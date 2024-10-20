
import React, { useEffect, useState } from 'react';

const API_BASE = process.env.NEXT_PUBLIC_API_BASE || 'http://localhost:8080';

export default function ProfilePage({ params }: any) {
  const id = params.id;
  const [data, setData] = useState<any>(null);
  useEffect(()=>{
    fetch(`${API_BASE}/v1/profile/${id}`).then(r=>r.json()).then(setData);
  },[id]);
  return <pre>{JSON.stringify(data, null, 2)}</pre>
}
