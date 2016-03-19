<div id="fplSubController" ng-controller="FPLSubscribeController as fplSub">
<form name="payForm" method="post" class="col-sm-12" ng-init="fplSub.fetchSubscribeDetails();<%=request.getAttribute("PAY_SUCCESS")=="SHOW"?"fplSub.paySuccess=true;":""%>" ng-submit="fplSub.SaveChanges(fplSub)">

<div id="form1" style="position:relative; top:200px;">

    	<div class="f-indicator">
        	<img src="support/images/fpsubscription1.png">
        </div>
        <div class="identify id1" name="identify">
        	<table id="tab1">
            <tr>
                	<td><input type="text" name="fpsubscriptionid" placeholder="Subscription ID" ng-model="fplSub.idText"  class="i-input"  disabled></td>
                    <td><input type="text" name="subscriptiondate" placeholder="Subscription Date"  class="i-input" value="{{fplSub.formatDate(fplSub.validationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>
            	<tr>
                	<td><input type="text" name="fpluname" placeholder="Financial Planners User Name"  ng-model="fplSub.email" value="" class="i-input fipl img" disabled></td>
					<td><input type="text" id="FPLNAME" name="name" placeholder="Name" value="" class="i-input name" ng-model="fplSub.name" required></td>
                </tr>
                <tr>
                    <td>
                    	<select class="i-input img" name="fplType" id="fplType" ng-model="fplSub.typeId" ng-change="fplSub.populatePlanDetails(fplSub)" required>
                    	 <option value="-1">Select Subscription Plan Type</option>
                    	 <option ng-selected="{{operator.id == fplSub.typeId}}"
            					ng-repeat="operator in fplSub.subtypes"
            					value="{{operator.id}}">
      							{{operator.type}}
    					</option>
                        </select>
                    </td>
                    <td><input type="text" placeholder="Duration in years"  name="subdur" ng-model="fplSub.duration" value="" class="i-input subdur" disabled></td>                    
                </tr>
                <tr>
                	<td>
                    	<input type="text" placeholder="Amount"  name="subdur" ng-model="fplSub.amount" value="" class="i-input amt" disabled>
                    </td>
                	
                </tr>
                <tr>
                  <td>
              			<span id="fplType_error" style="postion:relative; padding-top:70px; padding-left:50px; color:red; display:none;"> 
              				Please select Subscription Plan Type
            			</span>
                  </td>
                 </tr>   
            </table>
            <input type="button"  id="PAY" class="bt-end bt-cont" value="Proceed to pay" ng-click="fplSub.formId=2;fplSub.load(fplSub)">
            <input type="button" id="SAE" class="bt-end bt-sne" value="Save & Exit" ng-click="fplSub.action='GEN_SAVE'; fplSub.payNow(fplSub)">
            <input type="button" id="CANCEL" class="bt-end" value="Cancel">
            
            
       </div>

</div>

<div id="form2" style="display:none; position:relative; top:200px;" >

    	<div class="f-indicator">
        	<img src="support/images/fpsubscription2.png">
        </div>
        <table id="adinfo">

        	 <tr>
                	<td><input type="text" name="fpsubscriptionid" placeholder="Subscription ID"  class="i-input"  ng-model="fplSub.idText"  disabled></td>
                    <td><input type="text" name="subscriptiondate" placeholder="Subscription Date" class="i-input" value="{{fplSub.formatDate(fplSub.validationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>       
         </table>
         <div id="AGREESEC" class="agree" style="margin-top:60px"><input type="checkbox" id="agree" name="agree"  ng-model="fplSub.agreement" value="1"/>I have read and accepted the <a href="#">Terms & Conditions</a></div>
    		<span id="agree_error" style="postion:relative; padding-top:70px; padding-left:100px; color:red; display:none;"> 
              	Please accept Terms & Condition
            </span>
       
       
      
        <div class="identify id1" name="identify">
           <div id="tab1">
				<div class="t-select">
                	<div id="instdiv" class="t-s-ins t-show"  ng-click="!fplSub.isSubActive && fplSub.displayInstant(fplSub)" style="border-right: 1px solid white;"><p>Instant Subscription</p></div>
                    <div id="chqdiv" class="t-s-chq" ng-click="!fplSub.isSubActive && fplSub.displayCheque(fplSub)"><p>Cheque</p></div>
                </div>
                <div class="t-details">
                	<div class="t-d-ins t-show" id="instPay" style="display:block;">
                   
                    	<div class="t-d-opt">
                        	<input type="radio" id="PAYPAL" name="payment" ng-model="fplSub.mode" value="PAYPAL">
                            <img src="support/icons/paypal.png">
                        </div>
                        <div class="t-d-opt">
                        	<input type="radio" id="EWAY" name="payment" ng-model="fplSub.mode" value="EWAY">
                            <img src="support/icons/eway.png">
                        </div>
                        <div class="t-d-opt">
                        	<input type="radio" id="MASTER" name="payment" ng-model="fplSub.mode" value="MASTER">
                            <img src="support/icons/masterpass.png">
                        </div>
                        
                    </div>
                    <div id="chequePay" class="t-d-ins t-show" style="display:block;">
                    <input type="radio" name="payment" ng-model="fplSub.mode" value="CHEQUE" style="display:none;">
                    <br/>
                    	<p>Upon selecting the cheque payment option , the system will generate a pdf file of your application. Remember to print this PDF application and attach it with the cheque and mail it to our mailing address. </p>

                    </div>
                </div>
           </div>
          
             <input type="button" id="payButton" style="background-color:#CE2B2C"; name="bt-save" value="Pay" class="bt-end bt-cont " ng-click="fplSub.action='GEN_TOKEN';fplSub.payNow(fplSub)">
             <input type="button" id="chequeButton" style="background-color:#CE2B2C; display:none;" name="bt-save" value="Print Proforma Invoice" class="bt-end bt-cont " ng-click="fplSub.generateProForma(fplSub)">
             <input type="button" id="invoiceButton" style="background-color:#CE2B2C; display:none;" name="bt-save" value="Print Invoice" class="bt-end bt-cont " ng-click="fplSub.generateInvoice(fplSub)">
             <input type="button" id="proformaButton" style="background-color:#CE2B2C; display:none;" name="bt-save" value="Print Proforma Invoice" class="bt-end bt-cont " ng-click="fplSub.printProforma(fplSub)">
             
             <input type="button" name="bt-cancel" value="Back" class="bt-end bt-cancel" onClick="load(1);">
             <input type="button" name="bt-cancel" value="Cancel" class="bt-end bt-cancel">
			
                <table id="tab2">
      			<tr>
                	<td style="font-size:16px;text-align:right;border:0;" class="i-input">Subscription Status</td>
                    <td>
            <div class="f-type" style="border:none;">
           		<div   class="f-t-ind">
                	<div class="outer"><div id="activeBox" class="inner"></div></div>
                    <p>Active</p>
                </div>
                <div class="f-t-cor">
                	<div class="outer"><div id="inActiveBox"  class="innerActive"></div></div>
                    <p>Inactive</p>
                </div>
			</div>

                    </td>
                </tr>
                 
            </table>
            
       		<table id="tab3" border="0">
            	<tr>
                    <td width="50%" align="center"><p>Subscription Start Date</p></td>
                    <td width="50%" align="center">Subscription End Date</td>                    
                </tr>
                <tr>
                    <td align="center"><input type="text" placeholder="Subscription Start Date" id="" name="subsdate" style=" text-align: center;" class="i-input subsdate datepicker1" value="{{fplSub.formatDate(fplSub.startDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                    <td align="center"><input type="text" placeholder="Subscription End Date" id="" name="subsdate" style=" text-align: center;" class="i-input subedate datepicker2"  value="{{fplSub.formatDate(fplSub.endDate) | date:'dd-MMM-yyyy'}}" disabled></td>                    
                </tr>
                
            </table>
       		<table id="tab4">
            	<tr>
                	<td style="font-size:14px;text-align:right;">Subscription Request Status</td>
                    <td>
                      	<select class="i-input" ng-model="fplSub.paymentStatus" disabled>
                      	
                        	<option value="RD">Request in draft mode</option>
                            <option value="IF">Payment failed via instant options</option>
                            <option value="UC">User Cancelled Payment via instant options</option>
                            <option value="PR">Payment Received</option>
                            <option value="CI">Cheque to be issued</option>
                            <option value="CC">Cheque Inward Collected</option>
                            <option value="CH">Cheque not Honored</option>
                        </select>

                    </td>
                </tr>
            	<tr>
                	<td style="font-size:16px;text-align:right;">Payment Reference Number</td>
                    <td><input type="text" name="payrefnum" ng-model="fplSub.transRef" disabled class="i-input payrefnum"></td>
                </tr>
            	<tr>
                	<td style="font-size:16px;text-align:right;">Payment Reciept Date</td>
                    <td><input type="text" name="payrecdate" value="{{fplSub.formatDate(fplSub.validationDate) | date:'dd-MMM-yyyy'}}"  disabled class="i-input payrecdate"></td>
                </tr>
            </table>
       </div>
</div>
</form>
</div>