#!/bin/bash

STATUS=0

parse_yaml() {
  local prefix=$2
  local s='[[:space:]]*' w='[a-zA-Z0-9_]*' fs=$(echo @|tr @ '\034')
  sed -ne "s|^\($s\)\($w\)$s:$s\"\(.*\)\"$s\$|\1$fs\2$fs\3|p" \
      -e "s|^\($s\)\($w\)$s:$s\(.*\)$s\$|\1$fs\2$fs\3|p"  $1 |
  awk -F$fs '{
    indent = length($1)/2;
    vname[indent] = $2;
    for (i in vname) {if (i > indent) {delete vname[i]}}
    if (length($3) > 0) {
       vn=""; for (i=0; i<indent; i++) {vn=(vn)(vname[i])("_")}
       printf("%s%s%s=\"%s\"\n", "'$prefix'",vn, $2, $3);
    }
  }'
}

stop_all () {
  sudo supervisorctl -u $config_username -p $config_password stop all
}

status () {
  sudo supervisorctl -u admin -p $config_password status
}

start () {
  local command=$1
  sudo supervisorctl -u $config_username -p $config_password start $command
}

start_and_wait () {
  local command=$1
  sudo supervisorctl -u $config_username -p $config_password start $command
  while [ true ]
  do
    count=`sudo supervisorctl -u $config_username -p $config_password status $command | grep RUNNING | wc -l`
    if [ $count -eq 0 ]
    then
      break
    fi
    echo -n .
    sleep 1
  done
  echo
  sudo supervisorctl -u $config_username -p $config_password status $command
}

check_status () {
  local command=$1
  local status=$2
  count=`sudo supervisorctl -u $config_username -p $config_password status $command | grep $status | wc -l`
  if [ $count -eq 0 ]
  then
    echo "Application $command is failed! Expected status is $status"
    STATUS=1
  fi
}


# ==================================================================================================
# Load configuration

CONFIG=$(dirname $0)/config.yml ||$(pwd)/config.yml
eval $(parse_yaml $CONFIG "config_")


# --------------------------------------------------------------------------------------------------
# Start provisioning...

# Stop all
stop_all

# Start DBs
start redis

# Start import
start_and_wait data:import

# Start analytic
start_and_wait data:analytic

# Start UI/api
start nginx

# Check statuses
check_status data:import EXITED
check_status data:analytic EXITED

check_status nginx RUNNING
check_status redis RUNNING

# Show status
status


# --------------------------------------------------------------------------------------------------
# Check result and exit
if [ $STATUS -ne 0 ]
then
  echo "Provisioning is failed!"
fi
exit $STATUS
