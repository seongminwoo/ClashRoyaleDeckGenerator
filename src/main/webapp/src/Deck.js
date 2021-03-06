import React, { Component, PropTypes } from 'react';
import 'whatwg-fetch'
import update from 'react-addons-update'
import querystring from 'querystring';

const DECK_API_URL = 'http://localhost:8090/decks';
const RANDOM_TYPE_LIST_URL = 'http://localhost:8090/randomTypes';
const ALL_CARDS_LIST_URL = 'http://localhost:8090/cards/name';

class DeckContainer extends Component {
	constructor() {
		super();
		this.state = {
			cards: [],
			randomType: "",
			costAverage: 0,
			randomTypes: [],
			allCards: [],
			filterSelected: "none",
			filterSelectedCards: []
		};
	}

	reload(randomType) {
		let url = DECK_API_URL + "?type=" + randomType;
		if(this.state.filterSelectedCards.length > 0) {
			let data = {
				cards : this.state.filterSelectedCards
			}

			url = url + "&" + querystring.stringify(data);
		}

		console.log("url : " + url);

		fetch(url)
			.then((response) => response.json())
			.then((responseData) => {
				console.log(responseData);
				let newState = update(this.state, {cards: {$set: responseData.cards}, randomType: {$set: responseData.randomType}, costAverage: {$set: responseData.costAverage}});
				this.setState(newState);
			})
			.catch((error) => {
				console.log('Error fetching and parsing data', error);
			})
	}

	handleRandomTypeChange(randomType) {
		this.reload(randomType)
	}

	handleCardFilterAdd(card) {
		console.log("handleCardFilterAdd : " + card);
		let newState = update(this.state, {filterSelectedCards: {$push: [card]}, filterSelected: {$set: card}})
		this.setState(newState);
	}

	loadRandomTypes() {
		fetch(RANDOM_TYPE_LIST_URL)
			.then((response) => response.json())
			.then((responseData) => {
				console.log(responseData);
				let newState = update(this.state, {randomTypes: {$set: responseData}})
				this.setState(newState);
			})
			.catch((error) => {
				console.log('Error fetching and parsing data', error);
			})
	}

	loadAllCardNames() {
		fetch(ALL_CARDS_LIST_URL)
			.then((response) => response.json())
			.then((responseData) => {
				console.log(responseData);
				let newState = update(this.state, {allCards: {$set: responseData}})
				this.setState(newState);
			})
			.catch((error) => {
				console.log('Error fetching and parsing data', error);
			})
	}

	componentDidMount() {
		this.loadRandomTypes();
		this.loadAllCardNames();
		this.reload("BALANCED");
	}

	render() {
		return (
			<div>
				<RandomTypeSelect onRandomTypeChange={this.handleRandomTypeChange.bind(this)} randomTypes={this.state.randomTypes} randomType={this.state.randomType} />
				<CardsFilterSelect onCardChange={this.handleCardFilterAdd.bind(this)} allCards={this.state.allCards} filterSelected={this.state.filterSelected} />
				<button onClick={this.reload.bind(this, this.state.randomType)}>Generate</button>
				<br/>
				<br/>
				<Deck cards={this.state.cards} costAverage={this.state.costAverage} filterSelectedCards={this.state.filterSelectedCards}/>
			</div>
		);
	}
}

class CardsFilterSelect extends Component {
	handleChange(event) {
		this.props.onCardChange(event.target.value)
	}

	render() {
		var allCardsOption = this.props.allCards.map((v) => (
			<option value={v} key={v}>{v}</option>
		));

		allCardsOption.unshift(<option value="none" key="none">-preservedCards-</option>);

		var cardsFilterSelect = <select value={this.props.filterSelected} onChange={this.handleChange.bind(this)}> + {allCardsOption} + </select>

		return(
			<span>
				{cardsFilterSelect}
			</span>
		)
	}
}

CardsFilterSelect.propTypes = {
	onCardChange: PropTypes.func.isRequired,
	allCards: PropTypes.arrayOf(PropTypes.string),
	filterSelected: PropTypes.string.isRequired
}

class RandomTypeSelect extends Component {
	handleChange(event) {
		this.props.onRandomTypeChange(event.target.value)
	}

	render(){
		var randomTypesOption = this.props.randomTypes.map((v) => (
			<option value={v} key={v}>{v}</option>
		));
		var randomTypesSelect = <select value={this.props.randomType} onChange={this.handleChange.bind(this)}> + {randomTypesOption} + </select>

		return(
			<span>
				{randomTypesSelect}
			</span>
		)
	}
}
RandomTypeSelect.propTypes = {
	onRandomTypeChange: PropTypes.func.isRequired,
	randomTypes: PropTypes.arrayOf(PropTypes.string),
	randomType: PropTypes.string.isRequired
}

// Renders a SearchBar and a CardList
// Passes down filterText state and handleUserInput callback as props
class Deck extends Component {
	render(){
		return(
			<div>
				<CardList cards={this.props.cards}/>
				<p>Average Elixir Cost: {this.props.costAverage}</p>
				<p>preservedCards : {this.props.filterSelectedCards.join()}</p>
			</div>
		)
	}
}
Deck.propTypes = {
	cards: PropTypes.arrayOf(PropTypes.object),
	costAverage: PropTypes.number.isRequired,
	filterSelectedCards: PropTypes.arrayOf(PropTypes.string)
}


// Pure component that receives both cards and filterText as props
// The component is responsible for actually filtering the
// cards before displaying them.
// It's considered a pure component because given the same
// cards and filterText props the output will always be the same.
class CardList extends Component {
	render(){
		var cards = this.props.cards.map(
			(card) => <Card key={card.name}
							name={card.name}
							nameKr={card.nameKr} />
		)

		var firstRow = cards.slice(0, 4);
		var secondRow = cards.slice(4);

		return(
			<div>
				<div>
					{firstRow}
				</div>
				<div>
					{secondRow}
				</div>
			</div>
		)
	}
}
CardList.propTypes = {
	cards: PropTypes.arrayOf(PropTypes.object)
}

class Card extends Component {
	render() {
		let imgUrl = "./img/cards/" + this.props.name.toLowerCase().replace(/ /gi, '_').replace(/\./gi, '') + ".png"
		let cardNameKr = this.props.nameKr;
		return (
				<img src={imgUrl} width="100px" height="100px" title={cardNameKr} alt={cardNameKr}/>
		);
	}
}
Card.propTypes = {
	name: PropTypes.string.isRequired,
	nameKr: PropTypes.string.isRequired
}

export default DeckContainer;