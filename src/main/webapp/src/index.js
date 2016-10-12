import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, IndexRoute } from 'react-router';
import App from './App';
import Home from './Home.js';
import CardSearchMenu from './CardSearchMenu.js';
import './index.css';

ReactDOM.render(
  <Router>
    <Route path="/" component={App}>
        <IndexRoute component={Home} />
        <Route path="home" component={Home} />
        <Route path="cards" component={CardSearchMenu} />
    </Route>
  </Router>
  ,document.getElementById('root')
);