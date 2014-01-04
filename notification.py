#!/usr/bin/python

##this is just a code to generate notification in gnome	. Should build from this . Just	started	with notification mechanism ..


from gi.repository import Notify
Notify.init ("Hello world")
Hello=Notify.Notification.new ("SUBJECT: ","NOTIFICATION !!!","dialog-information")
Hello.show ()


