// Load all books initially
document.addEventListener("DOMContentLoaded", () => {
    loadAllBooks();
});


// ================= Load All Books =================
async function loadAllBooks() {

    try {

        const books = await getAllBooks();

        populateTable(books);

    } catch (error) {

        console.error("Failed to load books", error);

    }

}


// ================= Search Books =================
async function searchBooksHandler() {

    const keyword = document
        .getElementById("searchInput")
        .value
        .trim();

    const filterType = document
        .getElementById("filterType")
        .value;

    try {

        let books;

        if (keyword === "") {

            books = await getAllBooks();

        } else if (filterType === "all") {

            books = await searchBooks(encodeURIComponent(keyword));

        } else {

            const allBooks = await getAllBooks();
            const normalizedKeyword = keyword.toLowerCase();

            books = allBooks.filter(book => {
                const value = (book[filterType] || "")
                    .toString()
                    .toLowerCase();
                return value.includes(normalizedKeyword);
            });

        }

        populateTable(books);

    } catch (error) {

        console.error("Search failed", error);

        alert("Failed to search books");

    }

}

window.searchBooks = searchBooksHandler;


// ================= Populate Table =================
function populateTable(books) {

    const tbody = document.getElementById("searchTableBody");

    tbody.innerHTML = "";

    if (books.length === 0) {

        tbody.innerHTML = `
            <tr>
                <td colspan="7" class="text-center">
                    No books found
                </td>
            </tr>
        `;

        return;

    }

    books.forEach(book => {

        tbody.innerHTML += `
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

            </tr>
        `;

    });

}