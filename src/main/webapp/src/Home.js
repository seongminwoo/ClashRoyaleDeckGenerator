import React, { Component } from 'react';
import DeckContainer from './Deck.js';

class Home extends Component {
	render() {
		return (
			<div>
				<h3>Random Deck Generator</h3>
				<DeckContainer />
			</div>
		);
	}
}

export default Home;