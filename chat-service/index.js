var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var historyArr = [];

app.get('/', function(req, res){
  res.header('Access-Control-Allow-Origin', '*');
  res.sendFile(__dirname + '/index.html');
});

io.on('connection', function(socket){
  socket.on('chat message', function(msg){
    io.emit('chat message', msg);
  });
  
  socket.on('add', function(users){
	  //console.log(users);
	  if(users.status == "add"){
		  io.emit('add', users);
		  historyArr[users.toUser + users.fromUser] = [];
	  }
	  socket.on(users.toUser + users.fromUser, function(msg){
		  //console.log(msg.msg);
		  historyArr[users.toUser + users.fromUser].push(msg.user + " : " + msg.msg);
		  io.emit(users.toUser + users.fromUser, msg);
	  });
  });
  
  socket.on('history', function(chatId){
	  console.log(historyArr[chatId]);
	  io.emit('history', historyArr[chatId]);
  })
  
  socket.on('remove', function(chatId){
	  console.log("Removed: " + chatId);
	  socket.removeAllListeners(chatId);
	  historyArr[chatId] = [];
  });
});

http.listen(3000, function(){
  console.log('listening on *:3000');
});
