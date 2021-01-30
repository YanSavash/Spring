import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/Header";

class BookStore extends React.Component {
  render(){
    return(
      <div className="wrapper">
        <Header />
      </div>
    );
}
}

export default BookStore;