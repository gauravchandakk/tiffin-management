import { useState } from 'react'
import API from '../api/axios'

function Register() {

  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    role: 'PROVIDER'
  })

  const [message, setMessage] = useState('')

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value })
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      const response = await API.post('/api/auth/register', formData)
      setMessage(response.data)
    } catch (error) {
      setMessage('Something went wrong. Please try again.')
    }
  }

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">

        <h1 className="text-3xl font-bold text-center text-green-600 mb-6">
          Tiffin Management
        </h1>

        <h2 className="text-xl font-semibold text-center text-gray-700 mb-6">
          Create Account
        </h2>

        {message && (
          <p className="text-center text-green-600 mb-4">{message}</p>
        )}

        <form onSubmit={handleSubmit}>

          <div className="mb-4">
            <label className="block text-gray-700 font-medium mb-1">
              Full Name
            </label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              placeholder="Enter your name"
              className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:border-green-500"
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 font-medium mb-1">
              Email
            </label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="Enter your email"
              className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:border-green-500"
            />
          </div>

          <div className="mb-4">
            <label className="block text-gray-700 font-medium mb-1">
              Password
            </label>
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="Enter your password"
              className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:border-green-500"
            />
          </div>

          <div className="mb-6">
            <label className="block text-gray-700 font-medium mb-1">
              Register As
            </label>
            <select
              name="role"
              value={formData.role}
              onChange={handleChange}
              className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:border-green-500"
            >
              <option value="PROVIDER">Provider</option>
              <option value="CUSTOMER">Customer</option>
            </select>
          </div>

          <button
            type="submit"
            className="w-full bg-green-600 text-white py-2 rounded font-semibold hover:bg-green-700 transition"
          >
            Register
          </button>

        </form>

        <p className="text-center text-gray-600 mt-4">
          Already have an account?{' '}
          <span className="text-green-600 cursor-pointer font-medium">
            Login
          </span>
        </p>

      </div>
    </div>
  )
}

export default Register