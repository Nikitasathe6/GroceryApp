// ================= CART SYSTEM =================

let cart = [];

// Add To Cart
function addToCart(productName, price) {

    const existingProduct = cart.find(item => item.name === productName);

    if (existingProduct) {
        existingProduct.quantity += 1;
    } else {
        cart.push({
            name: productName,
            price: price,
            quantity: 1
        });
    }

    updateCart();
}

// Update Cart UI
function updateCart() {

    const cartItemsDiv = document.getElementById("cartItems");
    const totalPriceSpan = document.getElementById("totalPrice");
    const cartCountSpan = document.getElementById("cartCount");

    cartItemsDiv.innerHTML = "";

    let total = 0;
    let totalItems = 0;

    if (cart.length === 0) {
        cartItemsDiv.innerHTML = "<p>Your cart is empty</p>";
        totalPriceSpan.innerText = 0;
        cartCountSpan.innerText = 0;
        return;
    }

    cart.forEach(item => {

        total += item.price * item.quantity;
        totalItems += item.quantity;

        const itemDiv = document.createElement("div");
        itemDiv.classList.add("cart-item");

        itemDiv.innerHTML = `
            <div>
                <p><strong>${item.name}</strong></p>
                <p>₹${item.price} x ${item.quantity}</p>
            </div>
            <div class="cart-controls">
                <button onclick="decreaseQty('${item.name}')">-</button>
                <span>${item.quantity}</span>
                <button onclick="increaseQty('${item.name}')">+</button>
                <button class="remove-btn" onclick="removeItem('${item.name}')">X</button>
            </div>
        `;

        cartItemsDiv.appendChild(itemDiv);
    });

    totalPriceSpan.innerText = total;
    cartCountSpan.innerText = totalItems;
}

// Increase Quantity
function increaseQty(productName) {
    const product = cart.find(item => item.name === productName);
    product.quantity += 1;
    updateCart();
}

// Decrease Quantity
function decreaseQty(productName) {
    const product = cart.find(item => item.name === productName);

    if (product.quantity > 1) {
        product.quantity -= 1;
    } else {
        cart = cart.filter(item => item.name !== productName);
    }

    updateCart();
}

// Remove Item
function removeItem(productName) {
    cart = cart.filter(item => item.name !== productName);
    updateCart();
}

// ================= CART SLIDE =================

function openCart() {
    document.getElementById("cartPanel").classList.add("active");
    document.getElementById("overlay").classList.add("active");
}

function closeCart() {
    document.getElementById("cartPanel").classList.remove("active");
    document.getElementById("overlay").classList.remove("active");
}

// ================= PAYMENT =================

function openPayment() {
    if (cart.length === 0) {
        alert("Your cart is empty!");
        return;
    }

    document.getElementById("paymentModal").style.display = "flex";
}

function closePayment() {
    document.getElementById("paymentModal").style.display = "none";
}

function placeOrder() {

    const name = document.getElementById("fullName").value;
    const phone = document.getElementById("phone").value;
    const address = document.getElementById("address").value;

    if (!name || !phone || !address) {
        alert("Please fill all details!");
        return;
    }

    closePayment();
    closeCart();

    document.getElementById("successSlide").style.display = "flex";

    cart = [];
    updateCart();

    document.getElementById("fullName").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("address").value = "";
}

function closeSuccess() {
    document.getElementById("successSlide").style.display = "none";
}

// ================= SEARCH =================

function searchProducts() {

    const input = document.getElementById("searchInput").value.toLowerCase();
    const products = document.querySelectorAll(".product-card");

    products.forEach(product => {
        const name = product.querySelector("h3").innerText.toLowerCase();

        if (name.includes(input)) {
            product.style.display = "";
        } else {
            product.style.display = "none";
        }
    });
}

// ================= FILTER =================

function filterProducts(category) {

    const products = document.querySelectorAll(".product-card");

    products.forEach(product => {

        if (category === "all") {
            product.style.display = "";
        } else {
            if (product.classList.contains(category)) {
                product.style.display = "";
            } else {
                product.style.display = "none";
            }
        }
    });
}