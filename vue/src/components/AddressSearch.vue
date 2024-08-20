<template>
  <div class="text-center p-5">
       <h1>~Order Now~</h1>
       <h3>Check to See if You Are in Our Delivery Area!</h3>
 
     <!-- search bar --------->
     <div class="search-container text-center" ref="searchContainer">
       <input 
         type="text" 
         v-model="query" 
         @input="debounceFetchAddresses" 
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
       <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
     </div>
   </div>
 </template>
 
 <script>
 import axios from 'axios';
 import _ from 'lodash';
 
 export default {
     data() {
     return {
       query: '',
       addresses: [],
       latitude: 41.505493,
       longitude: -81.634346,
       radius: 16093.4,
       deliveryMessage: '',
       errorMessage: '',
       showAddress: false,
       debounceFetchAddresses: _.debounce(this.fetchAddresses, 300),
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
 
           this.addresses = response.data.features
             .filter(feature => feature.properties.country === 'United States')
             .map(feature => ({
               formatted: `${feature.properties.housenumber ? feature.properties.housenumber + ' ' : ''}${feature.properties.name}, ${feature.properties.city}, ${feature.properties.state}, ${feature.properties.postcode}, ${feature.properties.country}`,
               lat: feature.geometry.coordinates[1],
               lon: feature.geometry.coordinates[0],
             }));
 
           if (this.addresses.length === 0) {
             this.errorMessage = 'No addresses found. Please try a different query.';
           } else {
             this.errorMessage = '';
           }
         } catch (error) {
           console.error('Error fetching addresses:', error);
           this.errorMessage = 'There was an error fetching addresses. Please try again later.';
           this.addresses = [];
         }
       } else {
         this.addresses = [];
         this.errorMessage = '';
       }
     },
     checkDeliveryArea(address) {
       const distance = this.calculateDistance(this.latitude, this.longitude, address.lat, address.lon);
       if (distance <= this.radius) {
         this.deliveryMessage = `The address "${address.formatted}" is within our delivery area.`;
       } else {
         this.deliveryMessage = `The address "${address.formatted}" is not in our delivery area.`;
       }
       this.showAddress = false;
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
 
       return R * c;
     },  
     handleClickOutside(event) {
       const searchContainer = this.$refs.searchContainer;
       if (searchContainer && !searchContainer.contains(event.target)) {
         this.showAddress = false;
       }
     },
     mounted() {
       document.addEventListener('click', this.handleClickOutside);
     },
     beforeUnmount() {
       document.removeEventListener('click', this.handleClickOutside);
     },
   },        
 };
 </script>
 
 <style>
 .search-container {
   width: 100%;
   max-width: 400px;
   margin: 0 auto;
   padding: 10px;
   border: 1px solid #ccc;
   border-radius: 5px;
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
 
 .error-message {
   margin-top: 10px;
   color: red;
   font-weight: bold;
 }
 </style>