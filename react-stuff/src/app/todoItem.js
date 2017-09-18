var React = require ('react');
require ('./css/todoItem.css');

// Create a new todoItem component for each item
var TodoItem = React.createClass ({
  render: function () {
    return (
      <li>
        <div className="todo-item">
          <span className="todo-name">{this.props.item}</span>
          <span className="todo-delete" onClick={this.handleDelete}>x</span>
        </div>
      </li>
    );
  },

 // Since onDelete was passed as aprop, you need to access it this way.
  handleDelete: function () {
    this.props.onDelete (this.props.item);
  }
});

module.exports = TodoItem;
