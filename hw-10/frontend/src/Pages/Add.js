import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import AxiosApi from "../AxiosApi/AxiosApi";
import { Container, Form, Button } from "react-bootstrap";

class Add extends React.Component {
    constructor() {
        super();
        this.addBook = this.addBook.bind(this)
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangeGenreTitle = this.onChangeGenreTitle.bind(this);
        this.state = {
            book: {
                title: "",
                firstName: "",
                lastName: "",
                genreTitle: ""
            }
        };
    }

    addBook() {
        console.log(this.state.title)
        AxiosApi.addBook(this.state.book.title, this.state.book.firstName, this.state.book.lastName, this.state.book.genreTitle)
            .catch(e => {
                console.log(e);
            });
    }

    onChangeTitle(e) {
        const title = e.target.value;

        this.setState(function (prevState) {
            return {
                book: {
                    ...prevState.book,
                    title: title
                }
            };
        });
    }

    onChangeGenreTitle(e) {
        const genreTitle = e.target.value;

        this.setState(function (prevState) {
            return {
                book: {
                    ...prevState.book,
                    genreTitle: genreTitle
                }
            };
        });
    }

    onChangeFirstName(e) {
        const firstName = e.target.value;

        this.setState(function (prevState) {
            return {
                book: {
                    ...prevState.book,
                    firstName: firstName
                }
            };
        });
    }

    onChangeLastName(e) {
        const lastName = e.target.value;

        this.setState(function (prevState) {
            return {
                book: {
                    ...prevState.book,
                    lastName: lastName
                }
            };
        });
    }


    render() {
        return (
            <div>
                <h1> Добавление новой книги </h1>
                <Container style={{ width: '500px', padding: '50px' }}>
                    <Form>
                        <Form.Group >
                            <Form.Label>title</Form.Label>
                            <Form.Control
                                placeholder="Enter book title"
                                onChange={this.onChangeTitle}
                            />
                            <Form.Label>firstName</Form.Label>
                            <Form.Control
                                placeholder="Enter author firstName"
                                onChange={this.onChangeFirstName}
                            />
                            <Form.Label>lastName</Form.Label>
                            <Form.Control
                                placeholder="Enter author lastName"
                                onChange={this.onChangeLastName}
                            />
                            <Form.Label>genreTitle</Form.Label>
                            <Form.Control
                                placeholder="Enter genre title"
                                onChange={this.onChangeGenreTitle}
                            />
                        </Form.Group>
                        <Button variant="success" type="submit" onClick={this.addBook}>Add new book</Button>
                    </Form>
                </Container>
            </div>
        );
    }
}

export default Add;