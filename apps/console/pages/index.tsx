
import React, { useEffect, useState } from 'react';

const API_BASE = process.env.NEXT_PUBLIC_API_BASE || 'http://localhost:8080';

export default function Home() {
  const [profile, setProfile] = useState<any>(null);
  const [id, setId] = useState<string>('demo-user-1');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function fetchProfile() {
    setLoading(true); setError(null);
    try {
      const res = await fetch(`${API_BASE}/v1/profile/${id}`);
      const json = await res.json();
      setProfile(json);
    } catch (e:any) {
      setError(e?.message || 'failed');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { fetchProfile(); }, []);

  return (
    <div style={{fontFamily:'sans-serif', padding: 24}}>
      <h1>Consumer 360 Console</h1>
      <div style={{display:'flex', gap:8}}>
        <input value={id} onChange={e=>setId(e.target.value)} placeholder="Profile ID"/>
        <button onClick={fetchProfile}>Load Profile</button>
      </div>
      {loading && <p>Loading...</p>}
      {error && <p style={{color:'red'}}>Error: {error}</p>}
      <pre style={{background:'#f7f7f7', padding:12, marginTop:12}}>
        {JSON.stringify(profile, null, 2)}
      </pre>
    </div>
  );
}
