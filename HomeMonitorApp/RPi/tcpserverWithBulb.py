#!
import sys
from lifxlan import *
import socket



# How the program knows the command is done
End='\0'

def recv_end(the_socket):
	total_data=[];data=''
	while True:
			data=(the_socket.recv(8192)).decode()
			if End in data:
				total_data.append(data[:data.find(End)])
				break
			total_data.append(data)
			if len(total_data)>1:
				#check if end_of_data was split
				last_pair=total_data[-2]+total_data[-1]
				if End in last_pair:
					total_data[-2]=last_pair[:last_pair.find(End)]
					total_data.pop()
					break
	return (''.join(total_data))



# Create a TCP/IP socket
try:
	sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	#will allow to reconnect without timout. Should stop socket error already logged in
	from socket import *
	sock.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
except socket.error:
	print('Failed to create socket')
	sys.exit();

print('Socket created')

# The IP address and port you want to listen on
server_address = ("0.0.0.0", 5555)
print('starting up on %s port %s' % server_address)

# Then bind() is used to associate the socket with the 
# server address. In this case, the address is localhost,
# referring to the current server, and the port number is 5555.
sock.bind(server_address)

# Calling listen() puts the socket into server mode
# Listen for incoming connections
sock.listen(1)

# The variable that holds the return message
# --MUST-- END IN A NEWLINE OR CLIENT WILL HANG SERVER
retMessage = "\n"
status = "On"

i = 0
j = 0

while (status == "On"):
	
	if (j == 0):
		connection, client_address = sock.accept()
		j = 1
	# Wait for a connection
	#print('waiting for a connection\n')

	# accept() returns an open connection between the server and client,
	# along with the address of the client. The connection is actually a
	# different socket on another port (assigned by the kernel). Data is read
	# from the connection with recv_end() and transmitted with sendall().

	#print('connection from', client_address)

	data = recv_end(connection)
	command = data.split(',')
	print('Message from: %s "%s"'% (client_address[0], data))
	if (command[0] == "on"):
		print('Turning on light')
		## Command to turn light on
		retMessage = "LightOn\n"
		connection.sendall(retMessage.encode('utf-8'))
		print('end message from %s' % client_address[0])

	elif (command[0] == "quit"):
		print('Powering Down')
		retMessage = "Powering Down\n"
		status = "Off"
		# Clean up the connection
		connection.sendall(retMessage.encode('utf-8'))
		connection.close()
		exit(1);

	elif (command[0] == "color"):
		firstComamnd = firstComamnd = command.pop(0)
		command = map(int, command)
		bulb.set_color(command, True) 
		retMessage = firstComamnd + "\n\n"
		connection.sendall(retMessage.encode('utf-8'))

	elif (command[0] == "brightness"):
		firstComamnd = command.pop(0)
		command = map(int, command)
		bulb.set_brightness(command[2], True);
		retMessage = firstComamnd + "\n\n"
		connection.sendall(retMessage.encode('utf-8'))

	elif (command[0] == "power"):
		firstComamnd = command.pop(0)
		state = command.pop(0)
		bulb.set_power(state)   
		retMessage = firstComamnd + "\n\n"
		connection.sendall(retMessage.encode('utf-8'))

	elif (command[0] == "white"):
		firstComamnd = firstComamnd = command.pop(0)
		command = map(int, command)
		bulb.set_color(command, True) 
		retMessage = firstComamnd + "\n\n"
		connection.sendall(retMessage.encode('utf-8'))

	elif ((command[0] == "start") and (i == 0)):
		i = 1
		print("Discovering lights...")
		lifx = LifxLAN(1)
		devices = lifx.get_lights()
		bulb = devices[0]
		retMessage = "Connected!!" + "\n\n"
		connection.sendall(retMessage.encode('utf-8'))

	elif ((command[0] == "start") and (i == 1)):
		print("Discovering lights...")
		retMessage = "Connected!!" + "\n\n"
		connection.sendall(retMessage.encode('utf-8'))

	elif (command[0] == "close"):
		retMessage = "Disconnected!!" + "\n\n"
		connection.sendall(retMessage.encode('utf-8'))
		connection.close()
		j = 0

	else:
		print("Unknown message: %s" % data)
		retMessage = "Unknown command: " + data + "\n\n"
		connection.sendall(retMessage.encode('utf-8'))
		#print('end message from %s' % client_address[0])
