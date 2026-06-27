// Base URL
const BASE_URL = "http://localhost:8080";


// ================= BOOK APIs =================

// Get all books
async function getAllBooks() {
    const response = await fetch(`${BASE_URL}/books`);
    return await response.json();
}

// Get book by ID
async function getBookById(id) {
    const response = await fetch(`${BASE_URL}/books/${id}`);
    return await response.json();
}

// Add book
async function addBook(book) {
    const response = await fetch(`${BASE_URL}/books`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(book)
    });

    return await response.json();
}

// Update book
async function updateBook(id, book) {
    const response = await fetch(`${BASE_URL}/books/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(book)
    });

    return await response.json();
}

// Delete book
async function deleteBook(id) {
    await fetch(`${BASE_URL}/books/${id}`, {
        method: "DELETE"
    });
}


// ================= MEMBER APIs =================

// Get all members
async function getAllMembers() {
    const response = await fetch(`${BASE_URL}/members`);
    return await response.json();
}

// Add member
async function addMember(member) {
    const response = await fetch(`${BASE_URL}/members`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(member)
    });

    return await response.json();
}

// Delete member
async function deleteMember(id) {
    await fetch(`${BASE_URL}/members/${id}`, {
        method: "DELETE"
    });
}


// ================= SEARCH API =================

// Search books
async function searchBooks(keyword) {
    const response = await fetch(
        `${BASE_URL}/books/search?keyword=${keyword}`
    );

    return await response.json();
}


// ================= ISSUE BOOK =================

async function issueBook(issueRequest) {
    const response = await fetch(`${BASE_URL}/issues`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(issueRequest)
    });

    return await response.json();
}


// Get all issued books
async function getIssuedBooks() {
    const response = await fetch(`${BASE_URL}/issues`);
    return await response.json();
}


// ================= RETURN BOOK =================

async function returnBook(issueId) {
    const response = await fetch(
        `${BASE_URL}/issues/${issueId}/return`,
        {
            method: "PUT"
        }
    );

    return await response.json();
}


// ================= DASHBOARD =================

async function getDashboardStats() {
    const response = await fetch(`${BASE_URL}/dashboard`);
    return await response.json();
}