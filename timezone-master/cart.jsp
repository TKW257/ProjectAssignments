<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.ProductDAO, model.ProductDTO" %>
<%
    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
    if (cart == null) cart = new HashMap<>();

    ProductDAO dao = new ProductDAO();
    double total = 0;
%>

<!doctype html>
<html lang="zxx">
<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Watch shop | Cart</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<main>
  <div class="slider-area ">
      <div class="single-slider slider-height2 d-flex align-items-center">
          <div class="container">
              <div class="row">
                  <div class="col-xl-12">
                      <div class="hero-cap text-center">
                          <h2>Cart List</h2>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  </div>

  <section class="cart_area section_padding">
    <div class="container">
      <div class="cart_inner">
        <div class="table-responsive">
          <table class="table">
            <thead>
              <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <% for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                     int productId = entry.getKey();
                     int quantity = entry.getValue();
                     ProductDTO product = dao.getProductById(productId);
                     double price = product.getPrice().doubleValue();
                     double subtotal = price * quantity;
                     total += subtotal;
              %>
              <tr>
                <td><%= product.getName() %></td>
                <td>$<%= price %></td>
                <td>
                  <form action="cart" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="type" value="updateQty">
                    <input type="hidden" name="product_id" value="<%= productId %>">
                    <input type="number" name="quantity" value="<%= quantity %>" min="1">
                    <button class="btn btn-sm btn-outline-dark" type="submit">Update</button>
                  </form>
                </td>
                <td>$<%= subtotal %></td>
                <td>
                  <form action="cart" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="type" value="remove">
                    <input type="hidden" name="product_id" value="<%= productId %>">
                    <button class="btn btn-sm btn-danger" type="submit">Remove</button>
                  </form>
                </td>
              </tr>
              <% } %>
              <tr>
                <td colspan="3"><h5>Total</h5></td>
                <td colspan="2">$<%= total %></td>
              </tr>
            </tbody>
          </table>
          <div class="checkout_btn_inner float-right">
            <a class="btn_1" href="shop.jsp">Continue Shopping</a>
            <a class="btn_1 checkout_btn_1" href="checkout.jsp">Proceed to Checkout</a>
          </div>
        </div>
      </div>
    </div>
  </section>
</main>
<jsp:include page="footer.jsp" />
</body>
</html>
