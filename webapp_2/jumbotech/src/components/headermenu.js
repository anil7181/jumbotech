
import React  from 'react'
import { Link } from "react-router-dom";


export default function HeaderMenu() {
  return (
    <> 
   <header id="header" className="d-flex align-items-center">
    <div className="container d-flex align-items-center justify-content-between">

      <h1 className="logo"><a href="index.html">JUMBO MEDICAL TECHNOLOGIES<span>.</span></a></h1>

      <nav id="navbar" className="navbar">
        <ul>
          <li><Link className="nav-link scrollto active" to="/">Home</Link></li>
          <li><Link className="nav-link scrollto" to="/">About</Link></li>
          <li><Link to="/products" className="nav-link scrollto">Products</Link></li>
          {/* <li><a className="nav-link scrollto " href="#portfolio">Portfolio</a></li> */}
          {/* <li><a className="nav-link scrollto" href="#team">Team</a></li>
          <li className="dropdown"><a href="#"><span>Drop Down</span> <i className="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="#">Drop Down 1</a></li>
              <li className="dropdown"><a href="#"><span>Deep Drop Down</span> <i className="bi bi-chevron-right"></i></a>
                <ul>
                  <li><a href="#">Deep Drop Down 1</a></li>
                  <li><a href="#">Deep Drop Down 2</a></li>
                  <li><a href="#">Deep Drop Down 3</a></li>
                  <li><a href="#">Deep Drop Down 4</a></li>
                  <li><a href="#">Deep Drop Down 5</a></li>
                </ul>
              </li>
              <li><a href="#">Drop Down 2</a></li>
              <li><a href="#">Drop Down 3</a></li>
              <li><a href="#">Drop Down 4</a></li>
            </ul>
          </li> */}
          <li><a className="nav-link scrollto" href="#contact">Contact</a></li>
          {/* <li><Link className="nav-link scrollto" to="/admin/login">Login</Link></li> */}
        </ul>
        <i className="bi bi-list mobile-nav-toggle"></i>
      </nav>

    </div>
  </header>

    </>
    )

  }