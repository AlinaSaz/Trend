import React, { useState } from "react";
import axios from "axios";

function App() {
  const [threshold7Points, setThreshold7Points] = useState("");
  const [threshold8Points, setThreshold8Points] = useState("");

  const [data7Points, setData7Points] = useState([]);
  const [data8Points, setData8Points] = useState([]);

  const [error7Points, setError7Points] = useState(null);
  const [error8Points, setError8Points] = useState(null);

  const fetchData7Points = async () => {
    try {
      const response = await axios.get("/getStream7Points", {
        params: {
          threshold: threshold7Points,
        },
      });
      setData7Points(response.data);
      setError7Points(null);
    } catch (error7Points) {
      console.error("Ошибка запроса:", error7Points);
      setError7Points("Ошибка при загрузке данных");
      setData7Points([]);
    }
  };

    const fetchData8Points = async () => {
      try {
        const response = await axios.get("/getStream8Points", {
          params: {
            threshold: threshold8Points,
          },
        });
        setData8Points(response.data);
        setError8Points(null);
      } catch (error8Points) {
        console.error("Ошибка запроса:", error8Points);
        setError8Points("Ошибка при загрузке данных");
        setData8Points([]);
      }
    };


  const handleSubmit7Points = (e) => {
    e.preventDefault();
    fetchData7Points();
  };

   const handleSubmit8Points = (e) => {
      e.preventDefault();
      fetchData8Points();
    };

  return (
    <div class="container p-4 g-5">
      <div class="row g-4">
          <h1 class="text-center">Получить информацию о стабильности данных</h1>
        <div class="col">
           <form onSubmit={handleSubmit7Points}>
               <div class="row">
                 <div class="col">
                  <input
                    class="form-control"
                    type="text"
                    value={threshold7Points}
                    onChange={(e) => setThreshold7Points(e.target.value)}
                    placeholder="Введите скачок для ручья 7"
                  />
                 </div>
                 <div class="col">
                  <button type="submit" class="btn btn-primary mb-3">Получить данные</button>
                  </div>
                  </div>
                </form>

                {error7Points && <p style={{ color: "red" }}>{error7Points}</p>}

                <ul>
                  {data7Points.map((post) => (
                    <li key={post.id}>
                      <h3>{post.title}</h3>
                      <p>{post}</p>
                    </li>
                  ))}
                </ul>
        </div>
        <div class="col">
         <form onSubmit={handleSubmit8Points}>
                <div class="row">
                      <div class="col">
                       <input
                         class="form-control"
                         type="text"
                         value={threshold8Points}
                         onChange={(e) => setThreshold8Points(e.target.value)}
                         placeholder="Введите скачок для ручья 8"
                       />
                      </div>
                      <div class="col">
                       <button type="submit" class="btn btn-primary mb-3">Получить данные</button>
                      </div>
                      </div>
                     </form>

                     {error8Points && <p style={{ color: "red" }}>{error8Points}</p>}

                     <ul>
                       {data8Points.map((post) => (
                         <li key={post.id}>
                           <h3>{post.title}</h3>
                           <p>{post}</p>
                         </li>
                       ))}
                     </ul>
        </div>

      </div>
    </div>
  );
}

export default App;