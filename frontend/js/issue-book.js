// Load page
document.addEventListener("DOMContentLoaded", () => {
    loadMembers();
    loadBooks();
    loadIssuedBooks();
});


// Load Members Dropdown
async function loadMembers() {

    try {

        const members = await getAllMembers();

        const memberSelect = document.getElementById("memberId");

        memberSelect.innerHTML =
            '<option value="">Select Member</option>';

        members.forEach(member => {

            memberSelect.innerHTML += `
                <option value="${member.id}">
                    ${member.name}
                </option>
            `;

        });

    } catch (error) {

        console.error("Failed to load members", error);

    }

}


// Load Books Dropdown
async function loadBooks() {

    try {

        const books = await getAllBooks();

        const bookSelect = document.getElementById("bookId");

        bookSelect.innerHTML =
            '<option value="">Select Book</option>';

        books.forEach(book => {

            bookSelect.innerHTML += `
                <option value="${book.id}">
                    ${book.title}
                </option>
            `;

        });

    } catch (error) {

        console.error("Failed to load books", error);

    }

}


// Issue Book
async function issueBookHandler() {

    const issueRequest = {

        memberId: document.getElementById("memberId").value,

        bookId: document.getElementById("bookId").value,

        issueDate: document.getElementById("issueDate").value,

        dueDate: document.getElementById("dueDate").value

    };

    try {

        await issueBook(issueRequest);

        alert("Book issued successfully");

        document.getElementById("issueBookForm").reset();

        loadIssuedBooks();

    } catch (error) {

        console.error(error);

        alert("Failed to issue book");

    }

}


// Load Issued Books Table
async function loadIssuedBooks() {

    try {

        const issues = await getIssuedBooks();

        const tbody = document.getElementById("issueTableBody");

        tbody.innerHTML = "";

        issues.forEach(issue => {

            tbody.innerHTML += `
                <tr>

                    <td>${issue.id}</td>

                    <td>${issue.memberId}</td>

                    <td>${issue.bookId}</td>

                    <td>${issue.issueDate}</td>

                    <td>${issue.dueDate}</td>

                    <td>
                        <span class="badge bg-success">
                            Issued
                        </span>
                    </td>

                </tr>
            `;

        });

    } catch (error) {

        console.error("Failed to load issued books", error);

    }

}