<template>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>

        </tr>
        </thead>
        <tbody>
        <tr v-for="user in users" v-bind:key="user.id">
            <td>{{ user.id }}</td>
            <td>{{ user.username }}</td>
        </tr>
        </tbody>
    </table>
</template>


<script>

export default {
    props: ["users"],
};

</script>