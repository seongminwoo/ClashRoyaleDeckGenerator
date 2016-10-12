import React, { Component, PropTypes } from 'react';
import 'whatwg-fetch'

const CARD_API_URL = 'http://localhost:8080/cards';

class CardsContainer extends Component {
	constructor() {
		super();
		this.state = {
			cards: []
		};
	}

	componentDidMount() {
		fetch(CARD_API_URL)
			.then((response) => response.json())
			.then((responseData) => {
				this.setState({cards: responseData});
			})
			.catch((error) => {
				console.log('Error fetching and parsing data', error);
			});
	}

	render() {
		return (
			<CardsSearch cards={this.state.cards}/>
		);
	}
}

// Renders a SearchBar and a CardList
// Passes down filterText state and handleUserInput callback as props
class CardsSearch extends Component {
	constructor(){
		super();
		this.state={
			filterText: ''
		};
	}

	handleUserInput(searchTerm){
		this.setState({filterText:searchTerm})
	}

	render(){
		return(
			<div>
				<SearchBar filterText={this.state.filterText}
						   onUserInput={this.handleUserInput.bind(this)} />
				<CardList cards={this.props.cards}
						  filterText={this.state.filterText}/>
			</div>
		)
	}
}
CardsSearch.propTypes = {
	cards: PropTypes.arrayOf(PropTypes.object)
}


// Pure component that receives 2 props from the parent
// filterText (string) and onUserInput (callback function)
class SearchBar extends Component {
	handleChange(event){
		this.props.onUserInput(event.target.value)
	}

	render(){
		return <input type="search"
					  placeholder="search"
					  value={this.props.filterText}
					  onChange={this.handleChange.bind(this)} />
	}
}
SearchBar.propTypes = {
	onUserInput: PropTypes.func.isRequired,
	filterText: PropTypes.string.isRequired
}

// Pure component that receives both cards and filterText as props
// The component is responsible for actually filtering the
// cards before displaying them.
// It's considered a pure component because given the same
// cards and filterText props the output will always be the same.
class CardList extends Component {
	render(){
		let filteredCards = this.props.cards.filter(
			(card) => card.name.indexOf(this.props.filterText) !== -1 || card.nameKr.indexOf(this.props.filterText) !== -1
		);
		return(
			<ul>
				{filteredCards.map(
					(card) => <Card key={card.name}
										name={card.name}
										nameKr={card.nameKr} />
				)}
			</ul>
		)
	}
}
CardList.propTypes = {
	cards: PropTypes.arrayOf(PropTypes.object),
	filterText: PropTypes.string.isRequired
}

class Card extends Component {
	render() {
		return <li>{this.props.nameKr}({this.props.name})</li>
	}
}
Card.propTypes = {
	name: PropTypes.string.isRequired,
	nameKr: PropTypes.string.isRequired
}

export default CardsContainer;