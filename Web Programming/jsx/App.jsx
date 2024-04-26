
import { useState } from 'react'
import { useEffect } from 'react'
import SweetAlert2 from 'react-sweetalert2'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import './App.css'

function App() {
  const [id, setId] = useState('')
  const [data, setData] = useState(null)

  const handleClick = async () => {
      try {
          const data = await (await fetch(`https://swollenhippo.com/getProfileDetailsByAPIKey.php?APIKey=Mickey2022!&Codename=${id}`)).json()
          setData(data)
      } catch (err) {
          console.log(err.message)
      }
  }
  function checkResponse(data) {
      if (data) {
          console.log(data)
          return <div className='App'>{data.FirstName} {data.LastName}</div>;
      } else {
          return null;
      }

    }
  }
