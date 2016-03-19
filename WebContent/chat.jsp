<!DOCTYPE html>
<html><head>
	<title>FPL Chat</title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
  <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
  <script type="text/javascript" src="support/controller/stomp.js"></script>
 

</head><body lang="en">


<div id="first" class="box">
  <h2>Received</h2>
  <div></div>
  <form><input autocomplete="off" value="Type here..." id="textt" ></input></form>
</div>

<div id="connect1" class="box">
  <h2>connect1</h2>
  <button type="button" id="user1" onclick="myFunction(this)">Click Me session 1</button>
</div>

<div id="connect2" class="box">
  <h2>connect2</h2>
<button type="button" id="user2" onclick="myFunction(this)">Click Me session 2</button>
 <button type="button" id="user3" onclick="myFunction(this)">Click Me session 3</button>

<div id="second" class="box">
  <h2>Logs</h2>
  <div></div>
</div>


<script>
var aa = null;
var h = null;
function myFunction(ev) {
   // document.getElementById("demo").innerHTML = ev.id;
    var id1 = ev.id;
    console.log(id1);
    $("#textt").attr("arr",id1);
    
    var has_had_focus = false;
    var pipe = function(el_name, send) {
        var div  = $(el_name + ' div');
        var inp  = $(el_name + ' input');
        var form = $(el_name + ' form');

        var print = function(m, p) {
        	
             p = (p === undefined) ? '' : JSON.stringify(p);
             //console.log(m)
            div.append($("<code>").text(m ));
            
             
             div.append($("<code>").text(p ));
             
           // div.scrollTop(div.scrollTop() + 10000); 
        };

        if (send) {
            form.submit(function() {
                send(inp.val());
                inp.val('');
                return false;
            });
        }
        return print;
    };

  // Stomp.js boilerplate
 /*  var ws = new SockJS('http://' + window.location.hostname + ':15674/stomp');
  var client = Stomp.over(ws); */
  
  /* if (location.search == '?ws') {
      var ws = new WebSocket('ws://' + window.location.hostname + ':15674/ws');
  } else { */
	  
     // var ws = new SockJS('http://' + window.location.hostname + ':61613/stomp');
	  var ws = new WebSocket('ws://' + window.location.hostname + ':15674/ws');
 // }	
		  /* var url = "ws://localhost:61613/stomp";
		  var ws = new SockJS(url); */
		  var client = Stomp.over(ws);
			// SockJS does not support heart-beat: disable heart-beats
		  client.heartbeat.outgoing = 0;
		  client.heartbeat.incoming = 0;
		  client.debug = pipe('#second');
		
		  var print_first = pipe('#first', function(data) {
			  console.log("Length"+data.length);
			  if(data.length != 0)
			  {
			  console.log("if condotion success");
			   h = data;
			   console.log("h");
			   console.log(h)
			   console.log(data)
		   		  
			  }
			  console.log("called2a");
			  console.log(data);
			 // data={};
			//  data={idsda:"1",author:"aravind"}
			  console.log("hgg "+data);
			  console.log(aa);
			  console.log(id1);
			   alert(aa);
			   if(aa == id1)
				  {
				  alert("dsfdsfdfjd");
				  data = h;
				   client.send('/topic/'+id1, {"content-type":"text/plain"}, JSON.stringify(data));
				  }
			
		      
		  });

		  var on_connect = function(x) {
			  console.log("Connenct");
			  var pingeduser = 1;
		      id12 = client.subscribe("/topic/"+id1, function(d) {
		          //debugger;
		          //console.log(d.body.toString())
		          var quote = JSON.parse(d.body);
		          console.log("quote");
		          console.log(quote);
		          print_first(quote);
		      });
		  };
		  
		  var on_error =  function() {
			  //alert("called4a")
		    console.log('error');
		  };
		  
		  client.connect('guest', 'guest', on_connect, on_error, '/');
		
		  $('#first input').focus(function() {
			  //("called5a")
			   aa = $('#textt').attr("arr");
			
		      if (!has_had_focus) {
		          has_had_focus = true;
		          $(this).val("");
		      }
 		 });
		  
}


	</script>