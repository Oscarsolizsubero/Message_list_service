# Message_list_service

Para poder iniciar el programa se lo puede ejecutar mediante consola de Java o subirlo a un servidor ya sea Jboss o Tomcat.
el servidor utiliza el puerto 9090 y el contextPath: /SpringBootRestApi

por tanto el Path base del server sera: http://localhost:9090/SpringBootRestApi/api/

una vez este ejecutado el programa utilizaremos la aplicacion Postman para probar el programa se podra realizar las siguientes operaciones:

1.- User Management.

2.- Message Management.

# User Management

User Management tiene los siguientes metodos:

1.- ListAllUsers: el cual nos muestra una lista completa de los usuarios ya existentes, para mandar un request de este metodo se utiliza el valor= /user/ con el metodo GET. ex: http://localhost:9090/SpringBootRestApi/api/user/ 

2.- AddUser: el cual agrega un nuevo usuario, para mandar un request de este metodo se utiliza el valor= /user/{Username} con el metodo POST donde el username es el nombre del usuario el cual se quiere agregar. ex:http://localhost:9090/SpringBootRestApi/api/user/roberto

3.- ModifyUser: el cual modifica un usuario existente, para mandar un request de este metodo se utiliza el valor /user/{id} con el metodo PUT donde el id es el id del usuario el cual se quiere modificar, se debe enviar un body con el nuevo nombre del usuario. ex:http://localhost:9090/SpringBootRestApi/api/user/1 

4.- DeleteUser: el cual elimina un usuario existente, para mandar un request de este metodo se utiliza el valor /user/{Username} con el metodo DELETE donde el Username es el nombre de usuario el cual se quiere eliminar.

# Message Management

Message Management tiene los siguientes metodos:

1.- createmessage: el cual agrega un nuevo mensaje, para mandar un request de este metodo se utiliza el valor = /message/{emisor}/{receptor}?msg="{mensaje}" con el metodo POST, este metodo necesita un usuario emisor, un usuario receptor y un mensaje si el usuario no existe no se podra enviar el mensaje, una vez enviado el mensaje se guarda la fecha de enviado. ex: http://localhost:9090/SpringBootRestApi/api/message/Roberto/Marines?msg="Hola marines"

2.- showmessage: el cual muestra los mensajes que recibio un usuario receptor, para mandar un request de este metodo se utiliza el valor /message/{User} con el metodo GET donde User es el usuario receptor de mensajes, cuando se realiza un request de este metodo se guarda la fecha de visto. ex: http://localhost:9090/SpringBootRestApi/api/message/Marines
