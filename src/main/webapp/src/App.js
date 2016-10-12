import React, { Component } from 'react';
import './App.css';
import { Link } from 'react-router';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src="./img/logo/ClashRoyale_logo_version_02.png" className="App-logo" alt="logo" />
          <h2>Welcome to Clash Royale Deck Generator</h2>
        </header>
        <menu>
            <ul>
                <li><Link to="/" className="active">Home</Link></li>
                <li><Link to="/cards" className="active">Cards</Link></li>
            </ul>
        </menu>
        {this.props.children}
      </div>
    );
  }
}

export default App;
