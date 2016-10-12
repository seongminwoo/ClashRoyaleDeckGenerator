import React, { Component } from 'react';
import CardsContainer from './Card.js';

class CardSearchMenu extends Component {
	render() {
		return (
			<div>
				<h3>Card Search</h3>
				<CardsContainer />
			</div>
		);
	}
}

export default CardSearchMenu;