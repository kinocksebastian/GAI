#!/usr/bin/python

##this is just a code to generate notification in gnome	. Should build from this . Just	started	with notification mechanism ..


import pynotify
import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.bind(('127.0.0.1',5555))
sock.listen(1)
while 1:
	conn = sock.accept()[0]
	data = conn.recv(20)
	pynotify.init ("Hello world")
	if data:
		msg = pynotify.Notification ("Message",data,"dialog-information")
		msg.show ()






