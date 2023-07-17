<template>
    <div id="contactsTable">
      <h1>Results</h1>
      <div class="divTable">
        <form id="searchBox">
            <label for="searchTerm">Search for: &nbsp;</label>
            <input type="text" v-model="searchTerm" name="searchBox">
            <label for="resultsPerPage"> &nbsp;Results Per Page: &nbsp;</label>
            <input type="text" v-model.number="resultsPerPage" name="perPageBox">
            <select
                class="form-control"
                id="selectSort"
                v-model="sortOrder"
            >
              <option value="ASC" selected>Ascending</option>
              <option value="DESC">Descending</option>
                
            </select>

            <button type="submit" v-on:click.prevent="getSearchData">Search</button>
        </form>
        <div class="divTableHeading">
          <div class="divTableRow">
            <div class="divTableHead indexColumn">#</div>
            <div class="divTableHead nameColumn">Name</div>
            <div class="divTableHead phoneColumn">Phone Number</div>
          </div>
        </div>
        <div class="divTableBody">
          <div class="divTableRow" v-for="contact, index in contacts" :key="contact.id">
            <div class="divTableCell indexColumn"><p class="contactInt">{{index + 1}}</p></div>
            <div class="divTableCell nameColumn"><p class="contactInt">{{contact.name}}</p></div>
            <div class="divTableCell phoneColumn"><p class="contactInt">{{contact.phoneNumber}}</p></div>
          </div>
          <div class="pageLinks">
            <h3>Page: &nbsp;</h3>
            <a class="pagination" style="cursor: pointer" v-for="index in totalPages" :key="index" v-on:click.prevent="getNewPage(index)"><template v-if="index > 1">,</template>{{ index }}</a>
            
            <h3>&nbsp;&nbsp;Total Results:&nbsp;{{ totalResults }}</h3>
          </div>
        </div>
  
      </div>
  
    </div>
  </template>
  
  <script>
  import SearchService from "../services/SearchService.js";
  
  export default {
    name: "show-leader-board",
    data() {
      return {
        contacts: {},
        sortOrder: "ASC",
        searchTerm: "",
        resultsPerPage: 10,
        currentPage: 1,
        totalResults: 0,
      };
    },
    computed: {
        totalPages() {
            return Math.ceil(this.totalResults / this.resultsPerPage)
        }
    },
    created() {
      SearchService.getContacts(this.searchTerm, this.sortOrder, this.resultsPerPage, this.currentPage).then((response) => {
        this.contacts = response.data.contacts;
        this.totalResults = response.data.totalContacts;
        this.currentPage = response.data.currentPage;
      });
    },
    methods: {
      getSearchData() {
        SearchService.getContacts(this.searchTerm, this.sortOrder, this.resultsPerPage, this.currentPage).then((response) => {
            this.contacts = response.data.contacts;
            this.totalResults = response.data.totalContacts;
            this.currentPage = response.data.currentPage;
            //else alert
        });
      },
      getNewPage(pageNum) {
        SearchService.getContacts(this.searchTerm, this.sortOrder, this.resultsPerPage, pageNum).then((response) => {
            this.contacts = response.data.contacts;
            this.totalResults = response.data.totalContacts;
            this.currentPage = response.data.currentPage;
        });
      }
    },
  };
  </script>
  

<style>

  .divTableRow {
    display: flex;
  }

  .indexColumn {
    width: 6em;
  }

  .nameColumn {
    width: 20em;
  }

  .phoneColumn {
    width: 20em;
  }

  .pageLinks {
    display: flex;
    justify-content: right;
  }

  .pagination {
    font-size: large;
  }
</style>