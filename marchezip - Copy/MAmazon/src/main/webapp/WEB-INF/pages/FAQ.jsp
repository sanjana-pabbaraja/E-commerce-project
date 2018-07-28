<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/index">Home</a>
                        </li>
                        <li>FAQ</li>
                    </ul>

                </div>

                <div class="col-md-3">
                    <!-- *** PAGES MENU ***
 _________________________________________________________ -->
                    <div class="panel panel-default sidebar-menu">

                        <div class="panel-heading">
                            <h3 class="panel-title">Pages</h3>
                        </div>

                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <a href="termsandc.jpg">Terms and conditions</a>
                                </li>
                                <li>
                                    <a href="contact.jpg">Contact page</a>
                                </li>
                                <li>
                                    <a href="#">FAQ</a>
                                </li>

                            </ul>

                        </div>
                    </div>

                    <!-- *** PAGES MENU END *** -->


                  <div class="banner">
                        <a href="#">
                            <img src="img/banner.jpg" alt="sales 2014" class="img-responsive">
                        </a>
                    </div> 
                </div>

                <div class="col-md-9">


                    <div class="box" id="contact">
                        <h1>FAQ</h1>

                        <p class="lead">
                         <b> 1.How do i order online?</b><br>
                             Once you have finished adding items to your shopping cart and are ready to complete your transaction, move your cursor to the lower-right corner of the screen and click on the <b>Checkout</b>  button. You will then be transferred to our secure server and asked to either create a new account or place an order without an account.
                          <br><br>
                          <b> 2. How do I view what is  in my shopping cart?</b><br>
                              To view the contents of your cart, click on the <b>View cart</b> icon in the upper-right corner of your computer screen. Once you click on this icon, you can easily change the number of copies you want to purchase of a particular item in your cart by updating the quantity listed and then clicking the <b>Update cart</b> link. You can also delete any item in your cart by clicking the Remove check box to the left of that item and then updating your cart with the Update cart link.
                             
                          <br><br>
                          <b> 3. How will I know that you have received my order?</b><br>
                                After you complete the checkout process, a receipt will appear on your screen detailing your final order including shipping charges, your billing address, and the items that are being sent to your shipping address. Please keep this receipt for your reference.
                                You will also receive confirmation via e-mail that we have received your order. (Please be sure to enter your e-mail address correctly on the order form so that we can be sure to deliver your confirmation to you.)
                          </p>
                        <hr>
                        
                       
                    </div>


                </div>
                <!-- /.col-md-9 -->
            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->


        <!-- *** FOOTER ***
 _________________________________________________________ -->
       <jsp:include page="footer.jsp" />
       


    </div>
    <!-- /#all -->


    

   


</body>

</html>