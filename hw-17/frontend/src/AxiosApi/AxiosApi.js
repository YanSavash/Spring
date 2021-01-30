import axios from "axios"

export const AxiosApi = {
    get() {
        return axios.get("http://localhost:8080/bookstore/book");
    },

    getById(id) {
        return axios.get("http://localhost:8080/bookstore/bookById", { params: { id: id } });
    },

    updateBook(book) {
        return axios.put("http://localhost:8080/bookstore/update", book);
    },

    delete(id) {
        return axios.delete("http://localhost:8080/bookstore/delete", { params: { id: id } });
    },

    addBook(title, firstName, lastName, genreTitle) {
        var bodyFormData = new FormData();
        bodyFormData.set('title', title);
        bodyFormData.set('firstName', firstName);
        bodyFormData.set('lastName', lastName);
        bodyFormData.set('genreTitle', genreTitle);
        try {
            return axios.post("http://localhost:8080/bookstore/add", bodyFormData
            );
        }
        catch (e) {
            console.log(e)
        }
    }
}

export default AxiosApi;