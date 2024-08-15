<template>
    <div class="credit-card-form">
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="cardNumber">Card Number</label>
          <input
            type="text"
            id="cardNumber"
            v-model="cardNumber"
            placeholder="1234 5678 9012 3456"
            required
          />
        </div>
  
        <div class="form-group">
          <label for="cardHolder">Cardholder Name</label>
          <input
            type="text"
            id="cardHolder"
            v-model="cardHolder"
            placeholder="John Doe"
            required
          />
        </div>
  
        <div class="form-group">
          <label for="expiryDate">Expiration Date</label>
          <input
            type="text"
            id="expiryDate"
            v-model="expiryDate"
            placeholder="MM/YY"
            required
          />
        </div>
  
        <div class="form-group">
          <label for="cvv">CVV</label>
          <input
            type="text"
            id="cvv"
            v-model="cvv"
            placeholder="123"
            required
          />
        </div>
  
        <button type="submit">Submit Payment</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        cardNumber: "",
        cardHolder: "",
        expiryDate: "",
        cvv: "",
      };
    },
    methods: {
      submitForm() {
        // Submit
        alert(
          `Card Number: ${this.cardNumber}\nCardholder Name: ${this.cardHolder}\nExpiration Date: ${this.expiryDate}\nCVV: ${this.cvv}`
        );
        
      },
    },
  };
  </script>
  
  <style scoped>
  .credit-card-form {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  button {
    width: 100%;
    padding: 10px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #218838;
  }
  </style>
  