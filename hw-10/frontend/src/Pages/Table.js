import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import '../../node_modules/react-bootstrap-table/css/react-bootstrap-table.css';
import AxiosApi from "../AxiosApi/AxiosApi";
import PrepareState from "../components/PrepareState";

function onDeleteRow(rowKeys) {
  console.log("rowKeys")
  console.log(rowKeys)
  for (var i = 0; i < rowKeys.length; i++) {
    AxiosApi.delete(rowKeys[i])
  }
}

class Table extends Component {
  state = PrepareState;

  componentDidMount() {
    AxiosApi.get().then((response) => {
      this.setState({
        books: response.data
      })
    });
  }

  render() {
    const options = {
      afterDeleteRow: onDeleteRow
    }

    const selectRowProp = {
      mode: 'checkbox'
    }

    return (
      <>
        <div>
          <h1 style={{padding: '50px'}} className="text-center">All books</h1>
          <BootstrapTable data={this.state.books}
            striped
            deleteRow={true}
            selectRow={selectRowProp}
            options={options}>
            <TableHeaderColumn isKey dataField='id' dataSort={true}>
              Id
                    </TableHeaderColumn>
            <TableHeaderColumn dataField='title'>
              Title
                    </TableHeaderColumn>
            <TableHeaderColumn dataField='author'>
              Author
                    </TableHeaderColumn>
            <TableHeaderColumn dataField='genre'>
              Genre
                    </TableHeaderColumn>
            <TableHeaderColumn dataField='commentList'>
              Comment
                    </TableHeaderColumn>
          </BootstrapTable>
        </div>
      </>
    );
  }
}

export default Table;