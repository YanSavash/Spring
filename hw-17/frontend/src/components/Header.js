import React, { Component } from 'react'
import { Container, Navbar, Nav } from "react-bootstrap"
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import logo from './logo192.png'
import Table from '../Pages/Table'
import Add from '../Pages/Add'
import Id from '../Pages/Id'

export default class Header extends Component {
    render() {
        return (
            <>
                <Navbar fixed="top" collapseOnSelect expand="md" bg="success" variant="light" >
                    <Container>
                        <Navbar.Brand href="/">
                            <img
                                src={logo}
                                height="30"
                                width="30"
                                className="d-inline-block align-top"
                                alt="Logo"
                            /> Welcome to new book store
                        </Navbar.Brand>
                        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                        <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="mr-auto">
                                <Nav.Link href="/"> Home </Nav.Link>
                                <Nav.Link href="/add"> Add </Nav.Link>
                                <Nav.Link href="/id"> Id </Nav.Link>
                            </Nav>
                        </Navbar.Collapse>
                    </Container>
                </Navbar>
                <Router>
                    <Switch>
                        <Route exact path="/" component={Table} />
                        <Route exact path="/add" component={Add} />
                        <Route exact path="/id" component={Id} />
                    </Switch>
                </Router>
            </>
        )
    }
}