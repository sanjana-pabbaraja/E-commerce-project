<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Placing Order</title>
		
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.5/styles/default.min.css">
        <link rel="stylesheet" href="css/prism.css">

 
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

        <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>


        <style>
           
            .lpa-sdk {
                padding: 40px 15px;
                text-align: center;
            }
            .input-group {
                margin-bottom:10px;
            }
            #go-home {
                cursor:pointer;
            }
            pre code {
                overflow:scroll;
                word-wrap:normal;
                white-space:pre;
            }
            .jumbotroncolor {
                background:rgba(0, 153, 153, 0.15);
            }
            .jumbotroncodecolor {
                background:rgba(255, 204, 153, 0.4);
            }
            
			.container {
			    width: 860px;
			}
			.container .jumbotron, .container-fluid .jumbotron {
    padding-right: 30px;
    padding-left: 30px;
}
table th{
text-align: center;
}
        </style>
    </head>
    <body>
   <jsp:include page="header.jsp" />
   

  
   
      <input type="hidden" id="mws_access_key" value="AKIAIT3SWX5AWCIOAUXA">
      <input type="hidden" id="mws_secret_key" value="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">
      <input type="hidden" id="merchant_id" value="A3GTZ3FSK3A6SJ">
      <input type="hidden" id="client_id" value="amzn1.application-oa2-client.ca4388812db647f2af05dde48195bbc5">
        <div class="container">

            
            <div class="jumbotron jumbotroncolor" style="padding-top:0px;" id="api-content">
                <div id="section-content">
        <h3>Shipping Address & Payment</h3>

        <div class="text-center" style="margin-top:35px;">
            <div id="addressBookWidgetDiv" style="width:350px; height:240px; display:inline-block;"></div>
            <div id="walletWidgetDiv" style="width:350px; height:240px; display:inline-block;"></div>
            <div style="clear:both;"></div>

	        <form action='AuthorizeandConfirm' id='orderform' method='post'>
	            <input type="hidden" name="oro_id" id="oro_id" value="">
	            <input type="hidden" name="consent_token" id="consent_token" value="">
	            <input type="hidden" name="carttotal" id="carttotal" value="${myCart.amountTotal}">
	            
	            <div class="table-responsive" style="width: 70%;margin: auto;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                        <tr>
                                            <td>
                                            ${myCart.quantityTotal}
                                            </td>
                                            <td>
                                            <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/>
                                            </td>
                                        </tr>
                                    </tbody>                                  
                                </table>
                            </div>
   
	            <button id="place-order" class="btn btn-lg btn-success">Place Order</button>
	            <div id="ajax-loader" style="display:none;"><img src="images/ajax-loader.gif" /></div>
	        <form>
		</div>
 

        <script>
            window.onAmazonLoginReady = function() {
                amazon.Login.setClientId('amzn1.application-oa2-client.ca4388812db647f2af05dde48195bbc5');
            };
			//document.getElementById("carttotal").value=${myCart.quantityTotal};
            document.getElementById("consent_token").value = decodeURI(window.location.search.match(new RegExp('(?:[\?\&]access_token=)([^&]+)'))[1]);
        </script>
        <script type='text/javascript' src='https://static-na.payments-amazon.com/OffAmazonPayments/us/sandbox/js/Widgets.js'></script>

        <div id="addressBookWidgetDiv"></div>

        <div id="walletWidgetDiv"></div>

        <script>
            new OffAmazonPayments.Widgets.AddressBook({
                sellerId: 'A3GTZ3FSK3A6SJ',
                onOrderReferenceCreate: function(orderReference) {
                    console.log(orderReference);
                    console.log('addressBookWidget: ' + orderReference.getAmazonOrderReferenceId());
                    document.getElementById("oro_id").value = orderReference.getAmazonOrderReferenceId();
                  //  document.write('orderReferenceId=' + orderReference.getAmazonOrderReferenceId());
                },
                onAddressSelect: function(orderReference) {
                    console.log('on address select!');
                },
                displayMode : 'Edit',
                design: {
                    designMode: 'responsive'
                },
                onReady: function(orderReference) {
                },
                onError: function(error) {
                    console.log('addressBookWidget: ' + error.getErrorMessage());
                }
            }).bind("addressBookWidgetDiv");

            new OffAmazonPayments.Widgets.Wallet({
                sellerId: 'A3GTZ3FSK3A6SJ',
                onPaymentSelect: function(orderReference) {
                    console.log('on payment select!');
                },
                //onOrderReferenceCreate: function(orderReference) {
                //    console.log('walletWidget: ' + orderReference.getAmazonOrderReferenceId());
                //    document.getElementById("oro_id").value = orderReference.getAmazonOrderReferenceId();
                //},
                displayMode : 'Edit',
                design: {
                    designMode: 'responsive'
                },
                onError: function(error) {
                    console.log('walletWidget: ' + error.getErrorMessage());
                }
            }).bind("walletWidgetDiv");
        </script>
        </div><!--  end of <div id="section-content"> -->
        </div><!--  end of class="jumbotron jumbotroncolor" -->
        </div><!--  end of container -->
<jsp:include page="footer.jsp" />
    </body>
</html>
