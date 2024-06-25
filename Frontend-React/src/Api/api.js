
import axios from 'axios';

const cors = require("cors");

const app = express();
app.use(cors({
  origin:"https://cryptosphere-dun.vercel.app/"
}));

const LOCALHOST='https://treading-platform.onrender.com'


export const API_BASE_URL = LOCALHOST

const api = axios.create({
  baseURL: API_BASE_URL,
});

const token = localStorage.getItem('jwt');

api.defaults.headers.common['Authorization'] = `Bearer ${token}`;

api.defaults.headers.post['Content-Type'] = 'application/json';

export default api;
