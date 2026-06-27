// Load members when page loads
document.addEventListener("DOMContentLoaded", () => {
    loadMembers();
});


// ================= Load Members =================
async function loadMembers() {

    try {

        const members = await getAllMembers();

        const tableBody = document.getElementById("memberTableBody");

        tableBody.innerHTML = "";

        members.forEach(member => {

            tableBody.innerHTML += `
                <tr>

                    <td>${member.id}</td>

                    <td>${member.name}</td>

                    <td>${member.email}</td>

                    <td>${member.phoneNumber}</td>

                    <td>${member.address}</td>

                    <td>

                        <button
                            class="btn btn-warning btn-sm"
                            onclick="editMember(${member.id})">
                            Edit
                        </button>

                        <button
                            class="btn btn-danger btn-sm"
                            onclick="removeMember(${member.id})">
                            Delete
                        </button>

                    </td>

                </tr>
            `;

        });

    } catch (error) {

        console.error("Error loading members", error);

    }

}


// ================= Add Member =================
async function saveMember() {

    const member = {

        name: document.getElementById("name").value,

        email: document.getElementById("email").value,

        phoneNumber: document.getElementById("phoneNumber").value,

        address: document.getElementById("address").value

    };

    try {

        await addMember(member);

        alert("Member added successfully");

        document.getElementById("memberForm").reset();

        loadMembers();

    } catch (error) {

        console.error(error);

        alert("Failed to add member");

    }

}


// ================= Delete Member =================
async function removeMember(id) {

    if (!confirm("Are you sure you want to delete this member?")) {

        return;

    }

    try {

        await deleteMember(id);

        alert("Member deleted successfully");

        loadMembers();

    } catch (error) {

        console.error(error);

        alert("Failed to delete member");

    }

}


// ================= Edit Member =================
function editMember(id) {

    alert("Edit functionality will be implemented.");

}