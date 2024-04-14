import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";
import './App.css';
import { Header } from './shared/components/Header';
import { Home } from './shared/view/Home';
import { SignUp } from "./user/views/SignUp";
import { SignIn } from "./user/views/SignIn";
import { CreateStore } from "./store/views/CreateStore";
import { SearchStore } from "./store/views/ListStore";
import { CreateProduct } from "./product/views/CreateProduct";
import { ListProduct } from "./product/views/ListProduct";
import { UpdateProduct } from "./product/views/UpdateProduct";
import { SearchProduct } from "./product/views/SearchProduct";
import { UpdateStore } from "./store/views/UpdateStore";
import { DeleteStore } from "./store/views/DeleteStore";

function App() {
  return (
    <div className="App">

      <Router>
        <Header/>
        <Routes>
          <Route path="/" element={<Home/>}  />
          <Route path="/signup" element={<SignUp/>}/>
          <Route path="/signin" element={<SignIn/>}/>
          <Route path="/create-store" element={<CreateStore/>}/>
          <Route path="/update-store" element={<UpdateStore/>}/>
          <Route path="/search-store" element={<SearchStore/>}/>
          <Route path="/delete-store" element={<DeleteStore/>}/>
          <Route path="/create-product" element={<CreateProduct/>}/>
          <Route path="/list-product" element={<ListProduct/>}/>
          <Route path="/update-product/:id" element={<UpdateProduct/>}/>
          <Route path="/search-product/:id" element={<SearchProduct/>}/>
        </Routes>
      </Router>

    </div>
  );
}

export default App;
