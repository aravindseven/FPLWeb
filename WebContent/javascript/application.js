/*$(function () {
    "use strict";
    var header = $('#header');
    var rooms = $('#rooms');
    var content = [];
    var users = $('#users');
    var input = $('#input');
    var status = $('#status');
    var myName = false;
    var author = null;
    var logged = false;
    var socket = atmosphere;
    var subSocket;
    var transport = 'websocket';
    var fallbackTransport = 'long-polling'
    var connected = false;
    var uuid = 0;
    var customrooms;
    input.removeAttr('disabled').focus();
    var connectedRooms = ['dummy'];
    
    $("body").on("click",".chat_main_list",function(){
    	var pingeduser = $(this).attr("data-id");
    	
    	
    	$.ajax({
            type: "POST",
            url: "generatechatrooms.do",
            dataType: "json",
            data:{pingeduser:pingeduser},
            async:true,
            //contentType: "application/json; charset=utf-8",
            success: function (data) {
                //Success = true;//doesnt goes here
            		customrooms = data.Result;
            		author=data.Author;
            	    if(!customrooms==""){
            			connected = true;
            			connectedRooms.push(customrooms);
                		connect(customrooms);
            		return
            	}
            	
                console.log('chatroom value aftercreating: ', customrooms);
            },
            error: function (textStatus, errorThrown) {
               // Success = false;//doesnt goes here
            }
        });
    	
    });
    
    
    
    $(document).ready(function(){
    	
    });
    
   window.setInterval(function(){
    	$.ajax({
            type: "POST",
            url: "getchatrooms.do",
            dataType: "json",
            async:true,
            //contentType: "application/json; charset=utf-8",
            success: function (data) {
                //Success = true;//doesnt goes here
            	console.log("result:",data.Result);
            	for (var i=0;i<data.Result.length;i++)
            	{
            		
            		console.log("1st for result:",data.Result);
            		customrooms = data.Result[i];
            	
            		console.log("connectedrooms[]",connectedRooms.length);
            		console.log("connectedrooms[]",connectedRooms);
            	    	for (var j=0;j<connectedRooms.length;j++)
                    	{
            	    		console.log("2nd for  connectedrooms[] length",connectedRooms.length);
            	    		console.log("2nd for  connectedrooms[] ",connectedRooms[j]);
            	    		console.log("2nd for  customrooms",customrooms);
            	    		  if(connectedRooms[j]!=customrooms){
            		            	if(customrooms!=""){ 
            		    	     console.log("2nd for inside if connectedrooms[] length",connectedRooms.length);
            		    	     console.log("2nd for inside if connectedrooms[]",connectedRooms[j]);
            			         connected = true;
            			         var index = connectedRooms.indexOf('dummy');
            			         if (index > -1) {
            			        	 connectedRooms.splice(index, 1);
            			        	}
            			         connectedRooms.push(customrooms);
            			         author=customrooms.split("-")[1];
                		         connect(customrooms);
                		         abcd(6);
            		             }
            	    		  }
            		         
                    	}	
            		
            	    }
                console.log('chatroom value before checking: ', customrooms);
            },
            error: function (textStatus, errorThrown) {
               // Success = false;//doesnt goes here
            }
        });
    },10000);
   
   //popup
   function abcd(event) {
	   console.log("popup",event);
	    var str='#'+event;
	    $(str).show();
	    
	    }
   
   $('#inputbox1').keydown(function (e) {
	   console.log("inside chatboxmsgs");
        //input.keydown(function (e) {
        if (e.keyCode === 13) {
            var msg = $(this).val();
            console.log("inside chatboxmsgs",msg);
            $(this).val('');
            if (!connected) {
                connected = true;
                connect(msg);
                return;
            }

            // First message received is always the author's name
            if (author == null) {
                author = msg;
            }

            input.removeAttr('disabled').focus();
            // Private message
            if (msg.indexOf(":") !== -1) {
                var a = msg.split(":")[0];
                subSocket.push(atmosphere.util.stringifyJSON({ user: a, message: msg}));
            } else {
                subSocket.push(atmosphere.util.stringifyJSON({ author: author, message: msg, uuid: uuid }));
            }

            if (myName === false) {
                myName = msg;
            }
        }
    });
   $('#inputbox2').keydown(function (e) {
	   console.log("inside chatboxmsgs");
        //input.keydown(function (e) {
        if (e.keyCode === 13) {
            var msg = $(this).val();
            console.log("inside chatboxmsgs",msg);
            $(this).val('');
            if (!connected) {
                connected = true;
                connect(msg);
                return;
            }

            // First message received is always the author's name
            if (author == null) {
                author = msg;
            }

            input.removeAttr('disabled').focus();
            // Private message
            if (msg.indexOf(":") !== -1) {
                var a = msg.split(":")[0];
                subSocket.push(atmosphere.util.stringifyJSON({ user: a, message: msg}));
            } else {
                subSocket.push(atmosphere.util.stringifyJSON({ author: author, message: msg, uuid: uuid }));
            }

            if (myName === false) {
                myName = msg;
            }
        }
    });
   
   
   function dummy(){
	   alert(3434);
   }
   
    function connect(chatroom) {
        // We are now ready to cut the request
    	
    	
        var request = { url:'chat/' + chatroom,
            contentType: "application/json",
            logLevel: 'debug',
            transport: transport,
            trackMessageLength: true,
            reconnectInterval: 5000,
            fallbackTransport: fallbackTransport};

        request.onOpen = function (response) {
            content.html($('<p>', { text: 'Atmosphere connected using ' + response.transport }));
            console.log('onopen response: ', response);
            status.text('Choose name:');
            input.removeAttr('disabled').focus();
            transport = response.transport;
            uuid = response.request.uuid;
        };

        request.onReopen = function (response) {
            content.html($('<p>', { text: 'Atmosphere re-connected using ' + response.transport }));
            input.removeAttr('disabled').focus();
        };

        request.onMessage = function (response) {

            var message = response.responseBody;
            try {
                var json = atmosphere.util.parseJSON(message);
                console.log('response: ', response);
                console.log('message: ', message);
                console.log('json: ', json);
                
                
            } catch (e) {
                console.log('This doesn\'t look like a valid JSON: ', message);
                return;
            }

            input.removeAttr('disabled').focus();
            if (json.rooms) {
                rooms.html($('<h2>', { text: 'Current room: ' + chatroom}));

                var r = 'Available rooms: ';
                for (var i = 0; i < json.rooms.length; i++) {
                    r += json.rooms[i] + "  ";
                }
                rooms.append($('<h3>', { text: r }))
            }
           
            if (json.users) {
                var r = 'Connected users: ';
                for (var i = 0; i < json.users.length; i++) {
                    r += json.users[i] + "  ";
                }
                users.html($('<h3>', { text: r }))
            }

            if (json.author) {
                if (!logged && myName) {
                    logged = true;
                    status.text(myName + ': ').css('color', 'blue');
                } else {
                    var me = json.author == author;
                    var date = typeof(json.time) == 'string' ? parseInt(json.time) : json.time;
                    addMessage(json.author, json.message, me ? 'blue' : 'black', new Date(date));
                }
            }
        };

        request.onClose = function (response) {
        	
            subSocket.push(atmosphere.util.stringifyJSON({ author: author, message: 'disconnecting'+">"+content }));
        };

        request.onError = function (response) {
            content.html($('<p>', { text: 'Sorry, but there\'s some problem with your '
                + 'socket or the server is down' }));
            logged = false;
        };

        request.onReconnect = function (request, response) {
            content.html($('<p>', { text: 'Connection lost, trying to reconnect. Trying to reconnect ' + request.reconnectInterval}));
            input.attr('disabled', 'disabled');
        };

        subSocket = socket.subscribe(request);
    }

    function addMessage(author, message, color, datetime) {
    	var alignment;
		var timestamp;
		var imagealaign;
		var imagesrc;
    	//content.append(message);
    	if(color=='blue'){
    		alignment=leftchat;
    		timestamp=timestamp timestamp_l;
    		imagealaign=chat_image chat_image_left;
    		imagesrc=imgs/profilepicleft.png;
        	$('#dummychat ul').append("<li class=\"leftchat\"><div class=\"timestamp timestamp_l\">"+(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
                    + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())+"</div><div class=\"chatbox\">"+message+"</div><span class=\"chat_image chat_image_left\"><img src=\"imgs/profilepicleft.png\" /></span></li>");
	
    	}else{
    		alignment=rightchat;
    		timestamp=timestamp timestamp_r;
    		imagealaign=chat_image chat_image_right;
    		imagesrc=imgs/profilepicleft.png;
        	$('#dummychat ul').append("<li class=\"rightchat\"><div class=\"timestamp timestamp_r\">"+(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
                    + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())+"</div><div class=\"chatbox\">"+message+"</div><span class=\"chat_image chat_image_right\"><img src=\"imgs/profilepicleft.png\" /></span></li>");

    	}
    	
    	//$('#dummychat ul').html("<li class=\"leftchat\"><div class=\"timestamp timestamp_l\">02:30 AM</div><div class=\"chatbox\">"+message+"</div><span class=\"chat_image chat_image_left\"><img src=\"imgs/profilepicleft.png\" /></span></li>");
        content.push(author +'='+(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
            + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())
            + ': ' + message);
        
        console.log("content value",content);
    }
        
});*/
