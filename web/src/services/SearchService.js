import axios from 'axios'

const http = axios.create({
  baseURL: 'http://localhost:8080'
})
axios.defaults.params = {}
axios.defaults.params['term'] = ''

export default {
  getContacts(term, sort, qty, current) {
    return http.get(`/search?term=` + term + `&sort=` + sort + `&qty=` + qty + `&current=` + current)
  }
}