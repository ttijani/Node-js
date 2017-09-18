var React = require ('react');
var ReactDOM = require ('react-dom');

var TodoItem = require ('./todoItem');
var AddItem = require ('./addItem');
var About = require ('./about');

require ('./css/index.css');

import {Router, Route, browserHistory} from 'react-router';

var App = React.createClass ({
  render: function () {
    return (
      <Router history={browserHistory}>
        <Route path={'/'} component={TodoComponent}></Route>
        <Route path={'/about'} component={About}></Route>
      </Router>
    );
  }
});

// Create component
var TodoComponent = React.createClass({
  // Create the state for the todo component
  getInitialState: function () {
    return {
      todos: ['wash up', 'take a nap', 'eat some dope meat'],
      age: 30
    }
  },

  render: function () {
    var ager = setTimeout (function (){
        this.setState ({
          age: 35
        });
    }.bind(this), 5000);
    // We binded this to the function so it refers to the component still

    var todos = this.state.todos;
    // Makes a new array and each element has the surrrounding li tag as well
    todos = todos.map (function (item, index) {
      return (
        <TodoItem item={item} key={index} onDelete={this.onDelete} />
      );
    }.bind(this));

    return (
      <div id="todo-list">
        <p>The busiest people have the most leisure...</p>
        <p>{this.state.age}</p>
        <ul>{todos}</ul>
        <AddItem onAdd={this.onAdd} />
      </div>
    );
  },

  onDelete: function (item) {
    var updatedTodos = this.state.todos.filter (function (val, index) {
      return item !== val;
    });
    this.setState ({
      todos: updatedTodos
    });
  },

  onAdd: function (item) {
    var updatedTodos = this.state.todos;
    updatedTodos.push (item);
    this.setState ({
      todos: updatedTodos
    })
  }
});

// React DOM puts JSX in the page html
// ReactDOM.render (<TodoComponent mssg = "I like cheese" cheese = {myCheese}  />, document.getElementById('todo-wrapper'));
ReactDOM.render (<App />, document.getElementById('todo-wrapper'));
