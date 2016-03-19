 <!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	 <script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="support/script/atmosphere/atmosphere.js"></script>
     <script type="text/javascript" src="support/script/atmosphere/application.js"></script>
    
	 <link type="text/css" rel="stylesheet" href="support/css/custom/easy-responsive-tabs.css" />
     <script src="support/script/jQuery/jquery-1.6.3.min.js"></script>
   	 <script src="support/script/jQuery/easyResponsiveTabs.js" type="text/javascript"></script>
		
    <style type="text/css">
        .demo {
            width: 980px;
            margin: 0px auto;
        }
        .demo h1 {
                margin:33px 0 25px;
            }
        .demo h3 {
                margin: 10px 0;
            }
        pre {
            background: #fff;
        }
        @media only screen and (max-width: 780px) {
        .demo {
                margin: 5%;
                width: 90%;
         }
        .how-use {
                float: left;
                width: 300px;
                display: none;
            }
        }
        #tabInfo {
            display: none;
        }
       .checkboxmsg {
       opacity:0;
        } 
      
        table tbody tr{
        border-top: 1px solid #dddddd;
     	cursor: pointer;
        }
        
        
       #santhosh{
       display: none;
       }
        
    </style>
    <script type="text/javascript">
    $(document).ready(function(){
    	  $(".santhosh").mouseover(function() { 
    		  
              $(".checkboxmsg").css("opacity","1");
              
        });
 		$(".checkboxmsg").mouseover(function() { 
    		  
              $(".checkboxmsg").css("opacity","1");
              
        });
 		
 		$(".checkboxmsg").mouseout(function() { 
  		  
            $(".checkboxmsg").css("opacity","0");
            
      });
 		
    	  $(".santhosh") .mouseout(function() {        	
              $(".checkboxmsg").css("opacity","0");
              
          }); 
    	  
    	  
    	  $("#inside").click(function(){
    		  
    		  $("#santhosh").show();
    	  });
    });
    
    $(document).ready(function() {
        $('#selecctall').click(function(event) {  //on click 
            if(this.checked) { // check select status
                $('.checkboxmsg').each(function() { //loop through each checkbox
                    this.checked = true;  //select all checkboxes with class "checkbox1"
               	 $(".checkboxmsg").css("opacity","1");
                });
            }else{
                $('.checkboxmsg').each(function() { //loop through each checkbox
                    this.checked = false; //deselect all checkboxes with class "checkbox1"
                    $(".checkboxmsg").css("opacity","0");
                });         
            }
        });
        
    });
    
    </script>
</head>
<body>
    <div class="demo"  ng-controller="CommunicationController as communication">

        <!--vertical Tabs-->
        <div id="verticalTab">
       
            <ul class="resp-tabs-list">
          	    <li>New Message</li>
                <li><a href="#" ng-click="communication.inbox()">Inbox</a></li>
                <li><a href="#" ng-click="communication.sentmail()">Sent</a></li>
                <li><a href="#" ng-click="communication.trash()">Trash</a></li>
                <li>  Chat	</li>
				
                <!-- <li>Archive</li> -->
                
            </ul>
            <div class="resp-tabs-container">
	            <div>
		            <div class="row" ng-controller="CommunicationController as communication">
							<form name="newMessageForm" ng-submit="communication.save(message)"
								autocomplete="off" enctype="multipart/form-data">
		           		
					        <div border="0" width="60%" style="margin-left: 113px;">
					            <div id="message"></div>
					            <div >
					                <div >Send To </div>
					                <div><input type="text" name="recipient" size="50" 
													id="recipient" ng-model="message.recipient" required/></div>
					            </div>
					            <div>
					                <div>Subject </div>
					                <div><input type="text" name="subject" size="50" 
													id="subject" ng-model="message.subject" required/></div>
					            </div>
					            <div>
					                <div>Message </div>
					                <div><textarea rows="10" cols="39" name="content" 
													id="content" ng-model="message.content" required></textarea> </div>
					            </div>
					            <!-- <div>
					                <div>Attach file </div>
					                <div><input type="file" name="fileName" size="50" ng-model="message.fileName" required/></div>
					            </div> -->
					            <div>
					                <div colspan="2" align="center"><button class="btn btn-primary btn-medium" type="submit">Send</button></div>
					            </div>
					        </div>         
		    			</form>
		           </div> 
	            </div>
            
                <div id="inboxMessages">
                </div>
                
                <div id="sentMailBox">
                    
                </div>
                <div id="trash">
                
                </div>
                <div id="chatArea">
              		  <div id="chatContent"></div>
				    <div>
				        <span id="status">Connecting...</span>
				        <input type="text" id="input" disabled="disabled"/>
				    </div>
                </div>
              
               
            </div>
        </div>
        <br />
 

    </div>
    
    <div id="santhosh">
    
   
    </div>
    
     
</body>
<script type="text/javascript">
    $(document).ready(function () {
        /* $('#horizontalTab').easyResponsiveTabs({
            type: 'default', //Types: default, vertical, accordion           
            width: 'auto', //auto or any width like 600px
            fit: true,   // 100% fit in a container
            closed: 'accordion', // Start closed if in accordion view
            activate: function(event) { // Callback function if tab is switched
                var $tab = $(this);
                var $info = $('#tabInfo');
                var $name = $('span', $info);

                $name.text($tab.text());

                $info.show();
            }
        }); */

        $('#verticalTab').easyResponsiveTabs({
            type: 'vertical',
            width: 'auto',
            fit: true
        });
        
      

    });
</script>
</html>
 
 
 
 