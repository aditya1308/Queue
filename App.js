import logo from './logo.svg';
import './App.css';
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [fullName, setFullName] = useState('');
  const [cardNumber, setCardNumber] = useState('');
  const [expiry, setExpiry] = useState('');
  const [cvv, setCvv] = useState('');
  const [zipcode, setZipcode] = useState('');

  const handlePaymentSubmit = (e) => {
    e.preventDefault();
    // You can implement your payment processing logic here
    console.log({
      fullName,
      cardNumber,
      expiry,
      cvv,
      zipcode
    });
    // Reset the form fields after submission
    setFullName('');
    setCardNumber('');
    setExpiry('');
    setCvv('');
    setZipcode('');
  };
  return (
    <div className="container mt-5">
      <h2>Enter Payment Details</h2>
      <form onSubmit={handlePaymentSubmit}>
        <div className="mb-3">
          <label htmlFor="fullName" className="form-label">Full Name:</label>
          <input
            type="text"
            className="form-control"
            id="fullName"
            value={fullName}
            onChange={(e) => setFullName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="cardNumber" className="form-label">Card Number:</label>
          <input
            type="text"
            className="form-control"
            id="cardNumber"
            value={cardNumber}
            onChange={(e) => setCardNumber(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="expiry" className="form-label">Expiry:</label>
          <input
            type="text"
            className="form-control"
            id="expiry"
            value={expiry}
            onChange={(e) => setExpiry(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="cvv" className="form-label">CVV:</label>
          <input
            type="text"
            className="form-control"
            id="cvv"
            value={cvv}
            onChange={(e) => setCvv(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="zipcode" className="form-label">Zipcode:</label>
          <input
            type="text"
            className="form-control"
            id="zipcode"
            value={zipcode}
            onChange={(e) => setZipcode(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Submit Payment</button>
      </form>
    </div>
  );
}

export default App;
