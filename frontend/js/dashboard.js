document.addEventListener("DOMContentLoaded", () => {
    loadDashboard();
});

async function loadDashboard() {

    try {

        // Fetch data
        const books = await getAllBooks();
        const members = await getAllMembers();
        const issuedBooks = await getIssuedBooks();

        // Statistics
        document.getElementById("totalBooks").innerText = books.length;

        const availableBooks = books.reduce(
            (sum, book) => sum + book.availableQuantity,
            0
        );

        document.getElementById("availableBooks").innerText = availableBooks;

        document.getElementById("totalMembers").innerText = members.length;

        document.getElementById("issuedBooks").innerText = issuedBooks.length;

        // Populate Recent Books Table
        loadRecentBooks(books);

        // Populate Recent Issued Books Table
        loadRecentIssues(issuedBooks);

    } catch (error) {

        console.error("Dashboard loading failed", error);

    }

}


function loadRecentBooks(books) {

    const tbody = document.getElementById("recentBooksTable");

    tbody.innerHTML = "";

    books.slice(0, 5).forEach(book => {

        tbody.innerHTML += `
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.availableQuantity}</td>
            </tr>
        `;

    });

}


function loadRecentIssues(issues) {

    const tbody = document.getElementById("recentIssuesTable");

    tbody.innerHTML = "";

    issues.slice(0, 5).forEach(issue => {

        tbody.innerHTML += `
            <tr>
                <td>${issue.id}</td>
                <td>${issue.memberId}</td>
                <td>${issue.bookId}</td>
                <td>
                    <span class="badge bg-success">
                        Issued
                    </span>
                </td>
            </tr>
        `;

    });

}