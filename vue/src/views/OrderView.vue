<template>
    <head>
      <title>Sloppy Joe's Menu</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
<!-- header -->
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>                        
          </button>
          <!-- <a class="navbar-brand"> -->
            <img class="navbar-brand" src="@/assets/spLogo.png" alt="Joe's Sloppy Pizza" />
          <!-- </a> -->
        </div>

        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li class="active"><router-link v-bind:to="{name: 'home'}">Home</router-link></li>
            <li><router-link v-bind:to="{name: 'menu'}">Menu</router-link></li>
            <li><router-link v-bind:to="{name: 'order'}">Order Now</router-link></li>
            <li><router-link v-bind:to="{name: 'contact'}">Contact Us</router-link></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><router-link v-bind:to="{name: 'shopping-cart'}"><img class="navbar-brand" src="@/assets/shopping-cart2.png" alt="shopping cart img" 
                  style="width: 65px; height: 65px;"></router-link></li>
            <li><router-link v-bind:to="{name: 'login'}">Login</router-link></li>
            <li><router-link v-bind:to="{name: 'register'}">Register</router-link></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="text-center">
      <h2>~Order Now~</h2>
      <h3>Check to See if you are in Our Delivery Area!</h3>
    </div>

    <!-- search bar       --------->
    <div class="search-container">
    <input 
      type="text" 
      v-model="query" 
      @input="fetchAddresses" 
      placeholder="Search for an address"
      @focus="showAddress = true"
    />
    <ul v-if="showAddress && addresses.length">
      <li 
        v-for="(address, index) in addresses" 
        :key="index" 
        @click="checkDeliveryArea(address)"
      >
        {{ address.formatted }}
      </li>
    </ul>
    <p v-if="deliveryMessage" class="delivery-message">{{ deliveryMessage }}</p>
  </div>

  <!-- grid for photos -->
  <div class="grid-container">
    <div class="grid-item" v-for="(image, index) in images" :key="index">
      <img :src="image.src" :alt="image.alt">
      <button class="add-to-cart-btn" @click="addToCart(image)">Add to Cart</button>
    </div>
  </div>



  

      <footer class="container-fluid text-center">
      <p>&copy; 2024 Sloppy Jo's Pizza. All rights reserved.</p>
    </footer>
  </body>
</template>
  
  <script>
  import axios from 'axios';
  import Order from '../components/Order.vue';
 
  export default {
  data() {
    return {
      images: [
        { src: "@/assets/Pizzas1.png", alt: 'Pizza with Sausage/Pepperoni'},
        { src: '@/assets/beefPizza.jpg', alt: 'Beef Pizza' },
        { src: 'path/to/your/image3.jpg', alt: 'Image 3' },
        { src: 'path/to/your/image4.jpg', alt: 'Image 4' },
        { src: 'path/to/your/image5.jpg', alt: 'Image 5' },
        { src: 'path/to/your/image6.jpg', alt: 'Image 6' },
        { src: 'path/to/your/image7.jpg', alt: 'Image 7' },
        { src: 'path/to/your/image8.jpg', alt: 'Image 8' },
        { src: 'path/to/your/image9.jpg', alt: 'Image 9' },
      ],
      query: '',
      addresses: [],
      latitude: 41.505493,  // Latitude for 7100 Euclid Ave, Cleveland
      longitude: -81.634346, // Longitude for 7100 Euclid Ave, Cleveland
      radius: 16093.4, 
      deliveryMessage: '',
      showAddress: false,
    };
  },
  methods: {
    
    async fetchAddresses() {
      if (this.query.length > 2) {
        try {
          let response = await axios.get('https://photon.komoot.io/api/', {
            params: {
              q: this.query,
              lat: this.latitude,
              lon: this.longitude,
              limit: 5,
            },
          });

          // Filter and format addresses to include state and address number, and only include US results
          this.addresses = response.data.features
            .filter(feature => feature.properties.country === 'United States')
            .map(feature => ({
              formatted: `${feature.properties.housenumber ? feature.properties.housenumber + ' ' : ''}${feature.properties.name}, ${feature.properties.city}, ${feature.properties.state}, ${feature.properties.postcode}, ${feature.properties.country}`,
              lat: feature.geometry.coordinates[1],
              lon: feature.geometry.coordinates[0],
            }));
        } catch (error) {
          console.error('Error fetching addresses:', error);
          this.addresses = [];
        }
      } else {
        this.addresses = [];
      }
    },
    checkDeliveryArea(address) {
      const distance = this.calculateDistance(this.latitude, this.longitude, address.lat, address.lon);
      console.log(`Checking distance: ${distance} meters`); 
      if (distance <= this.radius) {
        this.deliveryMessage = `The address "${address.formatted}" is within our delivery area.`;
      } else {
        this.deliveryMessage = `The address "${address.formatted}" is not in our delivery area.`;
      }
    },
    calculateDistance(lat1, lon1, lat2, lon2) {
      const R = 6371e3; 
      const φ1 = lat1 * Math.PI / 180; 
      const φ2 = lat2 * Math.PI / 180;
      const Δφ = (lat2 - lat1) * Math.PI / 180;
      const Δλ = (lon2 - lon1) * Math.PI / 180;

      const a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                Math.sin(Δλ/2) * Math.sin(Δλ/2);
      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

      const distance = R * c; 
      return distance;
    },
    handleClickOutside(event) {
      const searchContainer = this.$refs.searchContainer;
      if (searchContainer && !searchContainer.contains(event.target)) {
        this.showAddresses = false;
      }
    },
    mounted() {
    document.addEventListener('click', this.handleClickOutside);
    },
    beforeUnmount() {
      document.removeEventListener('click', this.handleClickOutside);
    }
  },
};
  </script>
  
  <style scoped>
  body {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    background-color: #a9a9a9;
  }
  
  div.container {
    display: grid;
    grid-template-columns: 300px 1fr 250px;
    grid-template-rows: 100px 1fr 1fr;
    gap: 15px;
    background-color: #a9a9a9;
    grid-template-areas:
      "header header header"
      "main main main"
      "footer footer footer";
  }
  
  #page-header {
    box-shadow: 0 4px 5px 0 rgba(0,0,0,0.14), 0 1px 10px 0 rgba(0,0,0,0.12), 0 2px 4px -1px rgba(0,0,0,0.2);
    padding-left: 10px;
    padding-right: 10px;
    grid-area: header;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #daa520;
  }
  
  #page-footer {
    box-shadow: 0 4px 5px 0 rgba(0,0,0,0.14), 0 1px 10px 0 rgba(0,0,0,0.12), 0 2px 4px -1px rgba(0,0,0,0.2);
    padding-left: 10px;
    padding-right: 10px;
    grid-area: footer;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #e0ba20c1;
  }
  
  .resize-image {
    max-width: 200px;
    max-height: auto;
    margin-top: 20px;
  }
  
  footer {
      height: auto;
      padding: 10px 20px;
  }
  
  .footer-image {
    display: grid;
    place-items: center;
  }
  
  .inline-footer-links {
    display: grid;
    grid-template-columns: repeat(3, auto); 
    column-gap: 50px; 
    justify-content: center; 
  }
  
  .nav-link {
  display: flex; 
  list-style: none; 
  padding: 0;
  margin: 0;
}

.nav-link li {
  margin-right: 50px;
  font-size: 35px;
 
}

.nav-link li:last-child {
  margin-right: 50px; 
}

.nav-link a {
  text-decoration: none;
  color: black;
}

.nav-link a:hover {
  color: #0000ff;
}

/* search bar ////////////////// */
.search-container {
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
  padding: 10px;
  /* border: 1px solid #ccc; */
  border-radius: 5px;
  /* background-color: #f9f9f9; */
}

.search-container input {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

.search-container ul {
  margin: 0;
  padding: 0;
  list-style-type: none;
  border-top: 1px solid #ccc;
}

.search-container li {
  padding: 8px;
  border-bottom: 1px solid #ccc;
  background-color: #fff;
  cursor: pointer;
}

.search-container li:hover {
  background-color: #e6e6e6;
}

.delivery-message {
  margin-top: 10px;
  font-weight: bold;
}

/* grid for photos ////////////////// */
.grid-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px; /* Space between items */
  justify-content: center; /* Center items horizontally */
  align-items: center; /* Center items vertically */
  max-width: 900px; /* Adjust the width as needed */
  margin: 0 auto; /* Center the grid container */
}

.grid-item {
  flex: 1 1 calc(33.333% - 20px); /* 3 items per row, with gaps considered */
  box-sizing: border-box; /* Include padding and border in element's total width and height */
  background-color: #f0f0f0; /* Background color for demonstration */
  display: flex;
  flex-direction: column; /* Stack the image and button vertically */
  align-items: center; /* Center items horizontally */
  justify-content: center; /* Center items vertically */
  height: 200px; /* Fixed height for images */
  position: relative; /* Position relative to place the button absolutely */
  overflow: hidden; /* Hide overflow if needed */
}

.grid-item img {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover; /* Scale image to cover the container */
}

.add-to-cart-btn {
  position: absolute;
  bottom: 5px;
  background-color: #04454b;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  text-align: center;
}

.add-to-cart-btn:hover {
  background-color: #087a5d;
}
  
  
  </style>