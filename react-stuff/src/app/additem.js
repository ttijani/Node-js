var React = require ('react');
require ('./css/additem.css');

var addItem = React.createClass ({
  render: function () {
    return (
      <form id="add-todo" onSubmit={this.handleSubmit}>
        <input type="text" required ref="newItem" />
        <input type="submit" value="hit me" />
      </form>
    );
  },

  handleSubmit: function (e) {
    // Stops the default action for the event.
     e.preventDefault ();

     this.props.onAdd (this.refs.newItem.value);
  }
});

module.exports = addItem;
