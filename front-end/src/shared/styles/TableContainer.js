import styled from "styled-components";

export const TableContainer = styled.nav`
  #custom-table {
    font-family: Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
  }
  
  #custom-table td, #custom-table th {
    border: 1px solid #ddd;
    padding: 8px;
  }
  
  #custom-table tr:nth-child(even){background-color: #f2f2f2;}
  
  #custom-table tr:hover {background-color: #ddd;}
  
  #custom-table th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #333;
    color: white;
  }
`;