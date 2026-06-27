// Load books when page loads
document.addEventListener("DOMContentLoaded", () => {
    loadBooks();
});


// Fetch all books and display
async function loadBooks() {

    try {

        const books = await getAllBooks();

        const tableBody = document.getElementById("bookTableBody");

        tableBody.innerHTML = "";

        books.forEach(book => {

            tableBody.innerHTML += `
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.category}</td>
                    <td>${book.quantity}</td>
                    <td>${book.availableQuantity}</td>

                    <td>
                        <span class="badge bg-success">
                            Available
                        </span>
                    </td>

                    <td>

                        <button
                            class="btn btn-warning btn-sm"
                            onclick="editBook(${book.id})">
                            Edit
                        </button>

                        <button
                            class="btn btn-danger btn-sm"
                            onclick="removeBook(${book.id})">
                            Delete
                        </button>

                    </td>

                </tr>
            `;

        });

    } catch (error) {

        console.error("Error loading books:", error);

    }

}


// Add Book
async function saveBook() {

    const book = {

        title: document.getElementById("title").value,

        author: document.getElementById("author").value,

        category: document.getElementById("category").value,

        quantity: document.getElementById("quantity").value

    };

    try {

        await addBook(book);

        alert("Book added successfully");

        loadBooks();

    } catch (error) {

        console.error(error);

        alert("Failed to add book");

    }

}


// Delete Book
async function removeBook(id) {

    if (!confirm("Are you sure you want to delete this book?")) {

        return;

    }

    try {

        await deleteBook(id);

        alert("Book deleted successfully");

        loadBooks();

    } catch (error) {

        console.error(error);

        alert("Failed to delete book");

    }

}


// Edit Book
function editBook(id) {

    alert("Edit functionality will be implemented.");

}