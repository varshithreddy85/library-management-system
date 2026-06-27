// Load page
document.addEventListener("DOMContentLoaded", () => {
    loadIssuedBooksDropdown();
    loadReturnedBooks();
});


// ================= Load Issued Books Dropdown =================
async function loadIssuedBooksDropdown() {

    try {

        const issues = await getIssuedBooks();

        const issueSelect = document.getElementById("issueId");

        issueSelect.innerHTML =
            '<option value="">Select Issue Record</option>';

        issues.forEach(issue => {

            issueSelect.innerHTML += `
                <option value="${issue.id}">
                    Issue #${issue.id}
                </option>
            `;

        });

    } catch (error) {

        console.error("Failed to load issue records", error);

    }

}


// ================= Return Book =================
async function returnBookHandler() {

    const issueId = document.getElementById("issueId").value;

    if (!issueId) {

        alert("Please select an issue record");

        return;

    }

    try {

        await returnBook(issueId);

        alert("Book returned successfully");

        document.getElementById("returnBookForm").reset();

        loadIssuedBooksDropdown();

        loadReturnedBooks();

    } catch (error) {

        console.error(error);

        alert("Failed to return book");

    }

}


// ================= Load Returned Books =================
async function loadReturnedBooks() {

    try {

        const issues = await getIssuedBooks();

        const tbody = document.getElementById("returnTableBody");

        tbody.innerHTML = "";

        const returnedBooks = issues.filter(
            issue => issue.status === "RETURNED"
        );

        returnedBooks.forEach(issue => {

            tbody.innerHTML += `
                <tr>

                    <td>${issue.id}</td>

                    <td>${issue.memberId}</td>

                    <td>${issue.bookId}</td>

                    <td>${issue.issueDate}</td>

                    <td>${issue.returnDate}</td>

                    <td>
                        <span class="badge bg-secondary">
                            Returned
                        </span>
                    </td>

                </tr>
            `;

        });

    } catch (error) {

        console.error("Failed to load returned books", error);

    }

}