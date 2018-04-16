#!/bin/bash
id=$1
scp wolf_inns.zip $id@remote.eos.ncsu.edu:/afs/unity.ncsu.edu/users/${id:0:1}/$id

