import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import AxiosApi from "../AxiosApi/AxiosApi";
import { Container, Form, Button } from "react-bootstrap";

class Id extends Component {

    constructor() {
        super();
        this.getBookById = this.getBookById.bind(this)
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.update = this.update.bind(this)
        this.state = {
            book: { id: 0, title: "", author: "", genre: "", commentList: "" }
        };
    }

    getBookById() {
        AxiosApi.getById(this.state.id)
            .then(response => {
                this.setState({
                    book: response.data
                });
            })
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

    update() {
        AxiosApi.updateBook(this.state.book)
            .then(response => {
                this.setState({
                    book: response.data
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        return (
            <>
                <Container style={{ width: '500px', padding: '50px' }}>
                    <h1 className="text-center"> Введите id  нужной книги </h1>
                    <Form>
                        <Form.Group >
                            <Form.Label>Id</Form.Label>
                            <Form.Control
                                type="id"
                                placeholder="Enter id"
                                onKeyDown={(e) => e.keyCode === 13 ? e.preventDefault() : ''}
                                onChange={e => this.setState({ id: e.target.value })}
                            />
                            <Form.Text>
                                Id that used to get your book
                        </Form.Text>
                        </Form.Group>
                        <Button variant="success" type="button" onClick={this.getBookById}>Get book</Button>
                    </Form>
                </Container>
                <Form style={{ width: '500px', padding: '50px' }}>
                    <Form.Group >
                        <Form.Label>title</Form.Label>
                        <Form.Control
                            placeholder={this.state.book.title}
                            onChange={this.onChangeTitle}
                        />
                        <Form.Label>author</Form.Label>
                        <Form.Control
                            value={this.state.book.author}
                        />
                        <Form.Label>genre</Form.Label>
                        <Form.Control
                            value={this.state.book.genre}
                        />
                        <Form.Label>comment</Form.Label>
                        <Form.Control
                            value={this.state.book.commentList}
                        />
                    </Form.Group>
                    <Button variant="success" type="submit" onClick={this.update}>Update</Button>
                </Form>
            </>
        );
    }
}

export default Id;