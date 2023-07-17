import axios from 'axios'

const http = axios.create({
  baseURL: 'http://localhost:8080'
})
axios.defaults.params = {}
axios.defaults.params['term'] = ''

// axClient.interceptors.request.use((config) => {
//   // use config.params if it has been set
//   config.params = config.params || {}
//   // add any client instance specific params to config
//   config.params['api-key'] = process.env.VUE_APP_API_KEY
//   return config
// })

export default {
  getContacts(term, sort, qty, current) {
    return http.get(`/search?term=` + term + `&sort=` + sort + `&qty=` + qty + `&current=` + current)
  }
}
