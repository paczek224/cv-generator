// ══════════════════════════════════════════
//  THEMES
// ══════════════════════════════════════════
const THEMES = {
  midnight:{ name:'Midnight',
    header:'linear-gradient(135deg,#1e1b4b 0%,#4338ca 50%,#7c3aed 100%)',
    hbar:'linear-gradient(90deg,#6366f1,#a78bfa,#c084fc)', sb:'#1e1b4b',
    st:'#818cf8', sd:'#312e81', sl:'rgba(255,255,255,.82)', si:'#818cf8',
    slk:'#a5b4fc', sll:'rgba(255,255,255,.35)',
    sk:'linear-gradient(90deg,#818cf8,#c084fc)', cm:'rgba(255,255,255,.45)',
    mt:'#4338ca', md:'#eef2ff', badge:'linear-gradient(135deg,#4f46e5,#7c3aed)',
    co:'#6366f1', bu:'#818cf8', ep:'#6366f1', sm:'#818cf8' },
  ocean:{ name:'Ocean',
    header:'linear-gradient(135deg,#0c1a2e 0%,#0f3460 50%,#0e7490 100%)',
    hbar:'linear-gradient(90deg,#06b6d4,#0284c7,#0369a1)', sb:'#0c1a2e',
    st:'#22d3ee', sd:'#1a2f4a', sl:'rgba(255,255,255,.82)', si:'#22d3ee',
    slk:'#67e8f9', sll:'rgba(255,255,255,.35)',
    sk:'linear-gradient(90deg,#06b6d4,#0284c7)', cm:'rgba(255,255,255,.45)',
    mt:'#0e7490', md:'#ecfeff', badge:'linear-gradient(135deg,#0891b2,#0369a1)',
    co:'#0891b2', bu:'#22d3ee', ep:'#0891b2', sm:'#22d3ee' },
  forest:{ name:'Forest',
    header:'linear-gradient(135deg,#052e16 0%,#14532d 50%,#166534 100%)',
    hbar:'linear-gradient(90deg,#10b981,#059669,#047857)', sb:'#052e16',
    st:'#34d399', sd:'#14532d', sl:'rgba(255,255,255,.82)', si:'#34d399',
    slk:'#6ee7b7', sll:'rgba(255,255,255,.35)',
    sk:'linear-gradient(90deg,#10b981,#059669)', cm:'rgba(255,255,255,.45)',
    mt:'#065f46', md:'#d1fae5', badge:'linear-gradient(135deg,#059669,#047857)',
    co:'#059669', bu:'#34d399', ep:'#059669', sm:'#34d399' },
  crimson:{ name:'Crimson',
    header:'linear-gradient(135deg,#1a0505 0%,#881337 50%,#be123c 100%)',
    hbar:'linear-gradient(90deg,#f43f5e,#e11d48,#be123c)', sb:'#1a0505',
    st:'#fb7185', sd:'#3d0a16', sl:'rgba(255,255,255,.82)', si:'#fb7185',
    slk:'#fda4af', sll:'rgba(255,255,255,.35)',
    sk:'linear-gradient(90deg,#f43f5e,#fb7185)', cm:'rgba(255,255,255,.45)',
    mt:'#9f1239', md:'#ffe4e6', badge:'linear-gradient(135deg,#e11d48,#be123c)',
    co:'#e11d48', bu:'#fb7185', ep:'#e11d48', sm:'#fb7185' },
  amber:{ name:'Amber',
    header:'linear-gradient(135deg,#1c1004 0%,#451a03 50%,#78350f 100%)',
    hbar:'linear-gradient(90deg,#f59e0b,#d97706,#b45309)', sb:'#1c1004',
    st:'#fbbf24', sd:'#3d2504', sl:'rgba(255,255,255,.82)', si:'#fbbf24',
    slk:'#fcd34d', sll:'rgba(255,255,255,.35)',
    sk:'linear-gradient(90deg,#f59e0b,#d97706)', cm:'rgba(255,255,255,.45)',
    mt:'#92400e', md:'#fef3c7', badge:'linear-gradient(135deg,#d97706,#b45309)',
    co:'#d97706', bu:'#fbbf24', ep:'#d97706', sm:'#fbbf24' },
  steel:{ name:'Steel',
    header:'linear-gradient(135deg,#0c1e35 0%,#1e3a5f 50%,#1d4ed8 100%)',
    hbar:'linear-gradient(90deg,#3b82f6,#2563eb,#1d4ed8)', sb:'#0c1e35',
    st:'#60a5fa', sd:'#1e3a5f', sl:'rgba(255,255,255,.82)', si:'#60a5fa',
    slk:'#93c5fd', sll:'rgba(255,255,255,.35)',
    sk:'linear-gradient(90deg,#3b82f6,#2563eb)', cm:'rgba(255,255,255,.45)',
    mt:'#1d4ed8', md:'#dbeafe', badge:'linear-gradient(135deg,#2563eb,#1d4ed8)',
    co:'#2563eb', bu:'#60a5fa', ep:'#2563eb', sm:'#60a5fa' }
};

// ══════════════════════════════════════════
//  FONTS
// ══════════════════════════════════════════
const FONTS = [
  { name:'Inter',       label:'Inter' },
  { name:'Poppins',     label:'Poppins' },
  { name:'Montserrat',  label:'Montser.' },
  { name:'Raleway',     label:'Raleway' },
  { name:'Lato',        label:'Lato' },
  { name:'Nunito',      label:'Nunito' }
];

// ══════════════════════════════════════════
//  TEMPLATES
// ══════════════════════════════════════════
const TEMPLATES = [
  { id:'classic',   name:'Classic'   },
  { id:'executive', name:'Executive' },
  { id:'timeline',  name:'Timeline'  },
  { id:'lumina',    name:'Lumina'    },
];

// ══════════════════════════════════════════
//  STATE
// ══════════════════════════════════════════
let currentTheme    = 'midnight';
let currentFont     = 'Inter';
let currentTemplate = 'classic';
let photoDataUrl    = null;
let _lastCvData     = null;

// ══════════════════════════════════════════
//  THEME & FONT APPLICATION
// ══════════════════════════════════════════
function applyTheme(key) {
  currentTheme = key;
  document.querySelectorAll('.theme-btn').forEach(b => b.classList.toggle('active', b.dataset.theme === key));
  const doc = document.getElementById('cvDoc');
  if (!doc.children.length) return;
  const t = THEMES[key];
  const s = doc.style;
  s.setProperty('--cv-header', t.header);
  s.setProperty('--cv-hbar',   t.hbar);
  s.setProperty('--cv-sb-bg',  t.sb);
  s.setProperty('--cv-sb-title',t.st);
  s.setProperty('--cv-sb-div', t.sd);
  s.setProperty('--cv-sb-text',t.sl);
  s.setProperty('--cv-sb-icon',t.si);
  s.setProperty('--cv-sb-link',t.slk);
  s.setProperty('--cv-sb-label',t.sll);
  s.setProperty('--cv-skill',  t.sk);
  s.setProperty('--cv-cert-m', t.cm);
  s.setProperty('--cv-m-title',t.mt);
  s.setProperty('--cv-m-div',  t.md);
  s.setProperty('--cv-badge',  t.badge);
  s.setProperty('--cv-company',t.co);
  s.setProperty('--cv-bullet', t.bu);
  s.setProperty('--cv-edu-p',  t.ep);
  s.setProperty('--cv-sum-bdr',t.sm);
}

function applyFont(name) {
  currentFont = name;
  document.querySelectorAll('.font-card').forEach(b => b.classList.toggle('active', b.dataset.font === name));
  const doc = document.getElementById('cvDoc');
  if (doc) doc.style.fontFamily = `'${name}', sans-serif`;
}

function applyAppearanceToCvDoc() {
  applyTheme(currentTheme);
  applyFont(currentFont);
}

// ══════════════════════════════════════════
//  PHOTO
// ══════════════════════════════════════════
function handlePhoto(input) {
  const file = input.files[0];
  if (!file) return;
  const reader = new FileReader();
  reader.onload = e => {
    photoDataUrl = e.target.result;
    const img = document.getElementById('photoThumbImg');
    img.src = photoDataUrl;
    img.style.display = 'block';
    document.querySelector('.photo-thumb svg').style.display = 'none';
    document.getElementById('photoRemove').style.display = 'block';
    // Update live if CV is already rendered
    const cvPhoto = document.getElementById('cvHeaderPhoto');
    if (cvPhoto) { cvPhoto.src = photoDataUrl; cvPhoto.style.display = 'block'; }
  };
  reader.readAsDataURL(file);
}

function removePhoto(e) {
  e.stopPropagation();
  photoDataUrl = null;
  document.getElementById('photoFile').value = '';
  const img = document.getElementById('photoThumbImg');
  img.src = ''; img.style.display = 'none';
  document.querySelector('.photo-thumb svg').style.display = '';
  document.getElementById('photoRemove').style.display = 'none';
  const cvPhoto = document.getElementById('cvHeaderPhoto');
  if (cvPhoto) { cvPhoto.src = ''; cvPhoto.style.display = 'none'; }
}

// ══════════════════════════════════════════
//  BUILD APPEARANCE CONTROLS
// ══════════════════════════════════════════
function buildThemeButtons() {
  const wrap = document.getElementById('themeOptions');
  Object.entries(THEMES).forEach(([key, t]) => {
    const btn = document.createElement('button');
    btn.type = 'button';
    btn.className = 'theme-btn' + (key === currentTheme ? ' active' : '');
    btn.dataset.theme = key;
    btn.dataset.testid = `btn-theme-${key}`;
    btn.innerHTML = `
      <div class="theme-preview">
        <div class="tp-header" style="background:${t.header}"></div>
        <div class="tp-body">
          <div class="tp-sidebar" style="background:${t.sb}"></div>
          <div class="tp-main"><div class="tp-line"></div><div class="tp-line s"></div><div class="tp-line"></div></div>
        </div>
      </div>
      <span class="theme-name">${t.name}</span>`;
    btn.onclick = () => applyTheme(key);
    wrap.appendChild(btn);
  });
}

function buildFontButtons() {
  const wrap = document.getElementById('fontOptions');
  FONTS.forEach(f => {
    const btn = document.createElement('button');
    btn.type = 'button';
    btn.className = 'font-card' + (f.name === currentFont ? ' active' : '');
    btn.dataset.font = f.name;
    btn.dataset.testid = `btn-font-${f.name.toLowerCase()}`;
    btn.innerHTML = `<span class="fc-sample" style="font-family:'${f.name}',sans-serif">Ag</span><span class="fc-name">${f.label}</span>`;
    btn.onclick = () => applyFont(f.name);
    wrap.appendChild(btn);
  });
}

function buildTemplateButtons() {
  const previews = {
    classic:  `<div style="display:flex;height:100%"><div style="width:36%;background:#1e1b4b"></div><div style="flex:1;background:#f8fafc;display:flex;flex-direction:column;justify-content:center;gap:4px;padding:0 7px"><div style="height:2px;background:#c7d2fe;border-radius:1px;width:80%"></div><div style="height:2px;background:#e2e8f0;border-radius:1px;width:60%"></div><div style="height:2px;background:#e2e8f0;border-radius:1px;width:72%"></div><div style="height:2px;background:#e2e8f0;border-radius:1px;width:50%"></div></div></div>`,
    executive:`<div style="display:flex;flex-direction:column;height:100%"><div style="height:26%;background:linear-gradient(135deg,#1e1b4b,#4f46e5)"></div><div style="flex:1;display:flex"><div style="width:36%;background:#eef2ff"></div><div style="flex:1;background:#f8fafc;display:flex;flex-direction:column;justify-content:center;gap:4px;padding:0 7px"><div style="height:2px;background:#c7d2fe;border-radius:1px;width:80%"></div><div style="height:2px;background:#e2e8f0;border-radius:1px;width:58%"></div><div style="height:2px;background:#e2e8f0;border-radius:1px;width:70%"></div></div></div></div>`,
    timeline: `<div style="display:flex;flex-direction:column;height:100%"><div style="height:20%;background:linear-gradient(135deg,#1e1b4b,#4f46e5)"></div><div style="height:9%;background:#f8fafc;border-bottom:1px solid #e2e8f0"></div><div style="flex:1;background:white;padding:5px 8px;display:flex;flex-direction:column;gap:7px;justify-content:center">${[['70%','88%'],['55%','78%'],['65%','82%']].map(([a,b])=>`<div style="display:flex;align-items:flex-start;gap:4px"><div style="width:7px;height:7px;border-radius:50%;background:#4f46e5;flex-shrink:0;margin-top:1px"></div><div style="flex:1;display:flex;flex-direction:column;gap:2px"><div style="height:2px;background:#c7d2fe;width:${a};border-radius:1px"></div><div style="height:2px;background:#e2e8f0;width:${b};border-radius:1px"></div></div></div>`).join('')}</div></div>`,
    lumina:   `<div style="display:flex;flex-direction:column;height:100%"><div style="height:34%;background:linear-gradient(135deg,#1e1b4b,#4f46e5);display:flex;align-items:center;padding:0 8px;gap:6px"><div style="flex:1;display:flex;flex-direction:column;gap:2px"><div style="height:3px;background:white;width:52%;border-radius:1px"></div><div style="height:2px;background:rgba(255,255,255,.45);width:36%;border-radius:1px"></div><div style="height:1px;background:rgba(255,255,255,.3);width:45%;border-radius:1px;margin-top:2px"></div></div><div style="width:18px;height:18px;border-radius:50%;background:rgba(255,255,255,.25);border:1px solid rgba(255,255,255,.4);flex-shrink:0"></div></div><div style="height:4px;background:linear-gradient(90deg,#6366f1,#a78bfa,#c084fc)"></div><div style="flex:1;display:flex"><div style="width:36%;background:#eef2ff"></div><div style="flex:1;background:#f8fafc;display:flex;flex-direction:column;justify-content:center;gap:3px;padding:0 6px"><div style="height:2px;background:#c7d2fe;width:80%;border-radius:1px"></div><div style="height:2px;background:#e2e8f0;width:58%;border-radius:1px"></div><div style="height:2px;background:#e2e8f0;width:70%;border-radius:1px"></div></div></div></div>`,
  };
  const wrap = document.getElementById('tmplOptions');
  TEMPLATES.forEach(t => {
    const btn = document.createElement('button');
    btn.type = 'button';
    btn.className = 'tmpl-btn' + (t.id === currentTemplate ? ' active' : '');
    btn.dataset.tmpl = t.id;
    btn.dataset.testid = `btn-template-${t.id}`;
    btn.innerHTML = `<div class="tmpl-preview">${previews[t.id]}</div><span class="tmpl-name">${t.name}</span>`;
    btn.onclick = () => {
      currentTemplate = t.id;
      document.querySelectorAll('.tmpl-btn').forEach(b => b.classList.toggle('active', b.dataset.tmpl === t.id));
      if (_lastCvData) renderCv(_lastCvData);
    };
    wrap.appendChild(btn);
  });
}

// ══════════════════════════════════════════
//  MONTH PICKER
// ══════════════════════════════════════════
const MO = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];

function buildPicker(hiddenClass, initVal, isReq, testid) {
  const wrap = document.createElement('div');
  wrap.className = 'mp-wrap';
  const btn = document.createElement('button');
  btn.type = 'button';
  btn.className = 'mp-trigger' + (initVal ? '' : ' mp-empty');
  btn.innerHTML = `<span class="mp-text">${initVal ? fmtMo(initVal) : 'Select date'}</span><svg viewBox="0 0 24 24" fill="none" stroke-width="2"><rect x="3" y="4" width="18" height="18" rx="2"/><line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/><line x1="3" y1="10" x2="21" y2="10"/></svg>`;
  let curYear = initVal ? +initVal.split('-')[0] : new Date().getFullYear();
  const panel = document.createElement('div');
  panel.className = 'mp-panel';
  panel.innerHTML = `<div class="mp-nav"><button type="button" class="mp-arrow mp-prev">‹</button><span class="mp-year">${curYear}</span><button type="button" class="mp-arrow mp-next">›</button></div><div class="mp-grid">${MO.map((m,i)=>`<button type="button" class="mp-month" data-m="${i+1}">${m}</button>`).join('')}</div><button type="button" class="mp-clear">Clear</button>`;
  const hidden = document.createElement('input');
  hidden.type = 'hidden'; hidden.className = hiddenClass; hidden.value = initVal || '';
  if (isReq) hidden.required = true;
  if (testid) { btn.dataset.testid = testid; hidden.dataset.testid = testid + '-value'; }
  wrap.appendChild(btn); wrap.appendChild(panel); wrap.appendChild(hidden);
  let curValue = initVal || '';

  function refresh() {
    panel.querySelector('.mp-year').textContent = curYear;
    panel.querySelectorAll('.mp-month').forEach(mb => {
      mb.classList.toggle('mp-sel', curValue === `${curYear}-${String(mb.dataset.m).padStart(2,'0')}`);
    });
  }
  function setValue(val) {
    curValue = val || ''; hidden.value = curValue;
    if (curValue) { curYear = +curValue.split('-')[0]; btn.querySelector('.mp-text').textContent = fmtMo(curValue); btn.classList.remove('mp-empty'); }
    else { btn.querySelector('.mp-text').textContent = 'Select date'; btn.classList.add('mp-empty'); }
    refresh();
  }
  function open() { closeAllPickers(); panel.classList.add('mp-open'); refresh(); btn.style.borderColor='var(--primary)'; btn.style.boxShadow='0 0 0 3px var(--primary-glow)'; }
  function close() { panel.classList.remove('mp-open'); btn.style.borderColor=''; btn.style.boxShadow=''; }

  btn.addEventListener('click', e => { e.stopPropagation(); panel.classList.contains('mp-open') ? close() : open(); });
  panel.querySelector('.mp-prev').addEventListener('click', e => { e.stopPropagation(); curYear--; refresh(); });
  panel.querySelector('.mp-next').addEventListener('click', e => { e.stopPropagation(); curYear++; refresh(); });
  panel.querySelectorAll('.mp-month').forEach(mb => mb.addEventListener('click', e => { e.stopPropagation(); setValue(`${curYear}-${String(mb.dataset.m).padStart(2,'0')}`); close(); }));
  panel.querySelector('.mp-clear').addEventListener('click', e => { e.stopPropagation(); setValue(''); close(); });
  hidden._set = setValue; hidden._get = () => curValue;
  refresh(); return wrap;
}
function fmtMo(val) { if(!val) return ''; const [y,m]=val.split('-'); return `${MO[+m-1]} ${y}`; }
function closeAllPickers() { document.querySelectorAll('.mp-panel.mp-open').forEach(p=>{ p.classList.remove('mp-open'); const t=p.previousElementSibling; if(t){t.style.borderColor='';t.style.boxShadow='';} }); }
document.addEventListener('click', closeAllPickers);
function initPickers(el) { el.querySelectorAll('.picker-slot').forEach(s=>{ const p=buildPicker(s.dataset.cls,s.dataset.val||'',s.dataset.req==='true',s.dataset.testid||''); s.replaceWith(p); }); }
function setPicker(el,cls,val) { const h=el.querySelector('input.'+cls); if(h&&h._set) h._set(val); }

// ══════════════════════════════════════════
//  DYNAMIC LIST HELPERS
// ══════════════════════════════════════════
const RM = `<svg viewBox="0 0 24 24" fill="none" stroke-width="2.5"><line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/></svg>`;
function removeItem(btn){ btn.closest('.list-item').remove(); refreshBadges(); }
function refreshBadges(){
  [['#workList','Position'],['#eduList','Education'],['#socialList','Link'],['#certList','Certificate'],['#langList','Language']]
      .forEach(([s,l])=>document.querySelectorAll(`${s} .item-badge`).forEach((b,i)=>b.textContent=`${l} ${i+1}`));
}

function addWorkItem(data={}) {
  const n=document.querySelectorAll('#workList .list-item').length+1;
  const d=document.createElement('div'); d.className='list-item'; d.id=`workItem${n}`; d.dataset.testid=`work-item-${n}`;
  d.innerHTML=`<div class="list-item-header"><span class="item-badge">Position ${n}</span><button type="button" class="btn-remove" data-testid="work-remove-${n}" onclick="removeItem(this)">${RM} Remove</button></div>
  <div class="field-group">
    <div class="field full"><label>Job Title <span class="req">*</span></label><input type="text" id="workTitle${n}" data-testid="work-title-${n}" class="w-title" placeholder="Your job title at this company, e.g. Senior Software Engineer" value="${esc(data.title||'')}"/></div>
    <div class="field full"><label>Company, City, Country</label><input type="text" id="workCompany${n}" data-testid="work-company-${n}" class="w-company" placeholder="Company name, city and country, e.g. Acme Corp, Warsaw, Poland" value="${esc(data.companyName||'')}"/></div>
    <div class="field"><label>From <span class="req">*</span></label><div class="picker-slot" data-cls="w-from" data-val="${esc(data.from||'')}" data-req="true" data-testid="work-from-${n}"></div></div>
    <div class="field to-group ${data.currentJob?'disabled':''}"><label>To</label><div class="picker-slot" data-cls="w-to" data-val="${esc(data.currentJob?'':(data.to||''))}" data-req="false" data-testid="work-to-${n}"></div></div>
    <div class="toggle-row"><label class="toggle"><input type="checkbox" id="workCurrent${n}" data-testid="work-current-${n}" class="w-current" onchange="toggleCurrent(this)" ${data.currentJob?'checked':''}/><div class="toggle-track"><div class="toggle-thumb"></div></div></label><span class="toggle-label">Currently working here</span></div>
    <div class="field full"><label>Duties &amp; Technologies</label><textarea id="workDuties${n}" data-testid="work-duties-${n}" class="w-duties" rows="4" placeholder="Describe what you did here — rough notes are fine, AI will rewrite everything professionally.&#10;e.g. I automated regression tests, worked with REST APIs, used Jenkins and Docker for CI/CD">${esc(data.duties||'')}</textarea></div>
  </div>`;
  document.getElementById('workList').appendChild(d); initPickers(d);
}
function toggleCurrent(cb){ const item=cb.closest('.list-item'); const tg=item.querySelector('.to-group'); const h=item.querySelector('input.w-to'); if(cb.checked){tg.classList.add('disabled');if(h&&h._set)h._set('');}else{tg.classList.remove('disabled');} }

function addEduItem(data={}) {
  const n=document.querySelectorAll('#eduList .list-item').length+1;
  const d=document.createElement('div'); d.className='list-item'; d.id=`eduItem${n}`; d.dataset.testid=`edu-item-${n}`;
  d.innerHTML=`<div class="list-item-header"><span class="item-badge">Education ${n}</span><button type="button" class="btn-remove" data-testid="edu-remove-${n}" onclick="removeItem(this)">${RM} Remove</button></div>
  <div class="field-group">
    <div class="field full"><label>School / University &amp; Field of Study</label><input type="text" id="eduSchool${n}" data-testid="edu-school-${n}" class="e-school" placeholder="School or university name and field of study, e.g. University of Warsaw – Computer Science" value="${esc(data.school||'')}"/></div>
    <div class="field"><label>From <span class="req">*</span></label><div class="picker-slot" data-cls="e-from" data-val="${esc(data.from||'')}" data-req="true" data-testid="edu-from-${n}"></div></div>
    <div class="field"><label>To <span class="req">*</span></label><div class="picker-slot" data-cls="e-to" data-val="${esc(data.to||'')}" data-req="true" data-testid="edu-to-${n}"></div></div>
  </div>`;
  document.getElementById('eduList').appendChild(d); initPickers(d);
}

function addSocialItem(data={}) {
  const n=document.querySelectorAll('#socialList .list-item').length+1;
  const d=document.createElement('div'); d.className='list-item'; d.id=`socialItem${n}`; d.dataset.testid=`social-item-${n}`;
  d.innerHTML=`<div class="list-item-header"><span class="item-badge">Link ${n}</span><button type="button" class="btn-remove" data-testid="social-remove-${n}" onclick="removeItem(this)">${RM} Remove</button></div>
  <div class="field-group">
    <div class="field"><label>Platform</label><input type="text" id="socialName${n}" data-testid="social-name-${n}" class="s-name" placeholder="Platform name, e.g. LinkedIn or GitHub" value="${esc(data.name||'')}"/></div>
    <div class="field"><label>URL</label><input type="url" id="socialUrl${n}" data-testid="social-url-${n}" class="s-url" placeholder="Full profile URL, e.g. https://linkedin.com/in/yourname" value="${esc(data.url||'')}"/></div>
  </div>`;
  document.getElementById('socialList').appendChild(d);
}

function addCertItem(data={}) {
  const n=document.querySelectorAll('#certList .list-item').length+1;
  const d=document.createElement('div'); d.className='list-item'; d.id=`certItem${n}`; d.dataset.testid=`cert-item-${n}`;
  d.innerHTML=`<div class="list-item-header"><span class="item-badge">Certificate ${n}</span><button type="button" class="btn-remove" data-testid="cert-remove-${n}" onclick="removeItem(this)">${RM} Remove</button></div>
  <div class="field-group">
    <div class="field full"><label>Certificate Name</label><input type="text" id="certName${n}" data-testid="cert-name-${n}" class="c-name" placeholder="Certificate or course name, e.g. ISTQB Foundation Level" value="${esc(data.name||'')}"/></div>
    <div class="field"><label>Issuer</label><input type="text" id="certIssuer${n}" data-testid="cert-issuer-${n}" class="c-issuer" placeholder="Issuing organization, e.g. SJSI or Coursera" value="${esc(data.issuer||'')}"/></div>
    <div class="field"><label>Date <span class="req">*</span></label><div class="picker-slot" data-cls="c-date" data-val="${esc(data.date||'')}" data-req="true" data-testid="cert-date-${n}"></div></div>
  </div>`;
  document.getElementById('certList').appendChild(d); initPickers(d);
}

function addLanguageItem(data={}) {
  const n=document.querySelectorAll('#langList .list-item').length+1;
  const lvls=['Native','C2','C1','B2','B1','A2','A1'];
  const d=document.createElement('div'); d.className='list-item'; d.id=`langItem${n}`; d.dataset.testid=`lang-item-${n}`;
  d.innerHTML=`<div class="list-item-header"><span class="item-badge">Language ${n}</span><button type="button" class="btn-remove" data-testid="lang-remove-${n}" onclick="removeItem(this)">${RM} Remove</button></div>
  <div class="field-group">
    <div class="field"><label>Language</label><input type="text" id="langName${n}" data-testid="lang-name-${n}" class="l-name" placeholder="Language name, e.g. English or Polish" value="${esc(data.name||'')}"/></div>
    <div class="field"><label>Level</label><select id="langLevel${n}" data-testid="lang-level-${n}" class="l-level">${lvls.map(l=>`<option value="${l}" ${data.level===l?'selected':''}>${l==='Native'?'Native':l+' – '+{C2:'Mastery',C1:'Advanced',B2:'Upper Intermediate',B1:'Intermediate',A2:'Elementary',A1:'Beginner'}[l]}</option>`).join('')}</select></div>
  </div>`;
  document.getElementById('langList').appendChild(d);
}

// ══════════════════════════════════════════
//  FORM DATA
// ══════════════════════════════════════════
function getFormData() {
  return {
    firstName: v('firstName'), lastName: v('lastName'), position: v('position'),
    address: v('address'), phone: v('phone'), email: v('email'),
    socialLinks: [...document.querySelectorAll('#socialList .list-item')].map(el=>({ name:el.querySelector('.s-name').value.trim(), url:el.querySelector('.s-url').value.trim() })).filter(s=>s.name||s.url),
    workHistory: [...document.querySelectorAll('#workList .list-item')].map(el=>({ title:el.querySelector('.w-title').value.trim(), companyName:el.querySelector('.w-company').value.trim(), from:el.querySelector('input.w-from').value, to:el.querySelector('input.w-to').value, currentJob:el.querySelector('.w-current').checked, duties:el.querySelector('.w-duties').value.trim() })).filter(j=>j.title||j.companyName),
    educationList: [...document.querySelectorAll('#eduList .list-item')].map(el=>({ school:el.querySelector('.e-school').value.trim(), from:el.querySelector('input.e-from').value, to:el.querySelector('input.e-to').value })).filter(e=>e.school),
    skills: document.getElementById('skills').value.split('\n').map(s=>s.trim()).filter(Boolean),
    certifications: [...document.querySelectorAll('#certList .list-item')].map(el=>({ name:el.querySelector('.c-name').value.trim(), issuer:el.querySelector('.c-issuer').value.trim(), date:el.querySelector('input.c-date').value })).filter(c=>c.name),
    languages: [...document.querySelectorAll('#langList .list-item')].map(el=>({ name:el.querySelector('.l-name').value.trim(), level:el.querySelector('.l-level').value })).filter(l=>l.name),
    jobOffer: v('jobOffer'), enhanceCv: document.getElementById('enhanceCv').checked, language: document.getElementById('language').value
  };
}
function v(id){ return document.getElementById(id).value.trim(); }
function esc(s){ return String(s).replace(/&/g,'&amp;').replace(/"/g,'&quot;').replace(/</g,'&lt;'); }

// ══════════════════════════════════════════
//  PDF GENERATION
// ══════════════════════════════════════════
function enablePdfButton(firstName, lastName) {
  const dlBtn = document.getElementById('btnDownloadPdf');
  dlBtn.disabled = false;
  dlBtn.style.opacity = '';
  dlBtn.style.cursor  = '';
  dlBtn.onclick = async () => {
    const cvDoc   = document.getElementById('cvDoc');
    const sidebar = cvDoc.querySelector('.cv-sidebar');

    // Wait for web fonts to finish loading. Otherwise we measure with a fallback
    // font (usually shorter), the page comes out too short, and the footer spills
    // onto a second page once the real font renders at print time.
    if (document.fonts && document.fonts.ready) {
      try { await document.fonts.ready; } catch (e) {}
    }

    // The printed page is 210mm wide, but on screen the doc is narrower (~740px),
    // so text wraps to a different (taller) height than it will when printed.
    // Measure the doc AT the real print width so the page height matches.
    const PAGE_W_PX = 210 / 25.4 * 96;   // 210mm in CSS px @96dpi ≈ 793.7
    const PX_TO_MM  = 25.4 / 96;         // exact px → mm conversion
    const SAFETY_MM = 5;                 // tiny buffer for sub-pixel / print-dialog margins

    const prevWidth = cvDoc.style.width;
    cvDoc.style.width = PAGE_W_PX + 'px';                            // reflow at print width
    const heightMm = Math.ceil(cvDoc.getBoundingClientRect().height) * PX_TO_MM + SAFETY_MM;
    cvDoc.style.width = prevWidth;                                  // restore (no flicker)

    let ps = document.getElementById('_print_page_size');
    if (!ps) { ps = document.createElement('style'); ps.id = '_print_page_size'; document.head.appendChild(ps); }
    const sidebarCss = sidebar
        ? `.cv-doc-body{background:linear-gradient(to right,${getComputedStyle(sidebar).backgroundColor} ${sidebar.offsetWidth}px,white ${sidebar.offsetWidth}px)!important}.cv-sidebar{background:transparent!important}`
        : '';
    ps.textContent = `@media print{`
        + `@page{size:210mm ${heightMm}mm;margin:0}`
        + `html,body{margin:0!important;padding:0!important;background:#fff!important}`
        + `.container{margin:0!important;padding:0!important;max-width:none!important}`
        + `.cv-result-wrap{margin:0!important;padding:0!important}`
        + `.cv-doc{margin:0!important}`
        + sidebarCss
        + `}`;
    window.print();
  };
}

// ══════════════════════════════════════════
//  FIELD VALIDATION (phone & email)
// ══════════════════════════════════════════
const EMAIL_RE = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
// Allows an optional leading +, digits, spaces, dashes, dots and parentheses.
const PHONE_RE = /^\+?[0-9\s\-().]+$/;

function setFieldError(input, errEl, msg) {
  if (msg) { input.classList.add('invalid'); errEl.textContent = msg; errEl.classList.add('visible'); }
  else { input.classList.remove('invalid'); errEl.textContent = ''; errEl.classList.remove('visible'); }
  return !msg;
}

// Both fields are optional — an empty value is considered valid.
function validateEmail() {
  const input = document.getElementById('email'), errEl = document.getElementById('emailError');
  const val = input.value.trim();
  return setFieldError(input, errEl, (!val || EMAIL_RE.test(val)) ? '' : 'Please enter a valid email address, e.g. you@example.com');
}
function validatePhone() {
  const input = document.getElementById('phone'), errEl = document.getElementById('phoneError');
  const val = input.value.trim();
  const digits = (val.match(/\d/g) || []).length;
  const ok = !val || (PHONE_RE.test(val) && digits >= 7 && digits <= 15);
  return setFieldError(input, errEl, ok ? '' : 'Please enter a valid phone number (7–15 digits), e.g. +48 123 456 789');
}

function wireFieldValidation() {
  [['email', validateEmail], ['phone', validatePhone]].forEach(([id, fn]) => {
    const input = document.getElementById(id);
    input.addEventListener('blur', fn);
    // Re-validate while typing only once the field is already flagged, so the
    // error clears as soon as the input becomes valid.
    input.addEventListener('input', () => { if (input.classList.contains('invalid')) fn(); });
  });
}

// ══════════════════════════════════════════
//  SUBMIT
// ══════════════════════════════════════════
async function handleSubmit(e) {
  e.preventDefault();
  const data = getFormData();
  if (!data.firstName||!data.lastName){ showError('Please enter your first and last name.'); return; }
  if (!data.jobOffer){ showError('Please enter the target job / position.'); return; }

  const emailOk = validateEmail(), phoneOk = validatePhone();
  if (!emailOk || !phoneOk) {
    showError('Please correct the highlighted fields before generating your CV.');
    document.querySelector('.field-error.visible')?.closest('.field')?.scrollIntoView({ behavior:'smooth', block:'center' });
    return;
  }

  // reset download button
  const dlBtn = document.getElementById('btnDownloadPdf');
  dlBtn.disabled = true; dlBtn.style.opacity = '.45'; dlBtn.style.cursor = 'not-allowed'; _lastPdf = null;

  hideError(); setLoading(true);
  try {
    const res = await fetch('/api/cv/generate',{ method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify(data) });
    if (!res.ok) throw new Error((await res.text())||`HTTP ${res.status}`);
    const cvData = await res.json();
    renderCv(cvData);

    enablePdfButton(data.firstName, data.lastName);
  } catch(err) { showError(`Generation failed: ${err.message}`); }
  finally { setLoading(false); }
}
function setLoading(on){ document.getElementById('loadingBox').classList.toggle('visible',on); const b=document.getElementById('generateBtn'); b.disabled=on; b.textContent=on?'Generating…':'✨ Generate My CV'; }
function showError(msg){ const b=document.getElementById('errorBox'); document.getElementById('errorMsg').textContent=msg; b.classList.add('visible'); b.scrollIntoView({behavior:'smooth',block:'center'}); }
function hideError(){ document.getElementById('errorBox').classList.remove('visible'); }

// ══════════════════════════════════════════
//  RENDER CV — dispatcher
// ══════════════════════════════════════════
function renderCv(cv) {
  _lastCvData = cv;
  const el = document.getElementById('cvDoc');
  el.removeAttribute('style'); // clear inline theme vars — reapplied by applyAppearanceToCvDoc
  const renders = { classic: renderClassic, executive: renderExecutive, timeline: renderTimeline, lumina: renderLumina };
  (renders[currentTemplate] || renderClassic)(cv, el);
  applyAppearanceToCvDoc();
  const wrap = document.getElementById('cvResultWrap');
  wrap.classList.add('visible');
  wrap.scrollIntoView({ behavior:'smooth', block:'start' });
}

// shared helpers
function _svgPin()   { return `<svg viewBox="0 0 24 24" fill="none" stroke-width="2"><path d="M21 10c0 7-9 13-9 13S3 17 3 10a9 9 0 1118 0z"/><circle cx="12" cy="10" r="3"/></svg>`; }
function _svgPhone() { return `<svg viewBox="0 0 24 24" fill="none" stroke-width="2"><path d="M22 16.92v3a2 2 0 01-2.18 2 19.79 19.79 0 01-8.63-3.07A19.5 19.5 0 013.07 9.8a19.79 19.79 0 01-3.07-8.67A2 2 0 012 1h3a2 2 0 012 1.72c.127.96.361 1.903.7 2.81a2 2 0 01-.45 2.11L6.91 8.09a16 16 0 006 6l1.27-1.27a2 2 0 012.11-.45c.907.339 1.85.573 2.81.7A2 2 0 0122 16.92z"/></svg>`; }
function _svgMail()  { return `<svg viewBox="0 0 24 24" fill="none" stroke-width="2"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/><polyline points="22,6 12,13 2,6"/></svg>`; }
function _svgLink()  { return `<svg viewBox="0 0 24 24" fill="none" stroke-width="2"><path d="M10 13a5 5 0 007.54.54l3-3a5 5 0 00-7.07-7.07l-1.72 1.71"/><path d="M14 11a5 5 0 00-7.54-.54l-3 3a5 5 0 007.07 7.07l1.71-1.71"/></svg>`; }
function _photoTag(cls) { return `<img class="${cls}" id="cvHeaderPhoto" src="${photoDataUrl||''}" alt="" style="display:${photoDataUrl?'block':'none'}"/>`; }
function _duties(j) { const d=Array.isArray(j.duties)?j.duties:(j.duties?[j.duties]:[]); return d; }
function _period(j) { return j.currentJob?`${j.from||''} – Present`:[j.from,j.to].filter(Boolean).join(' – '); }
function _langsSidebarHtml(langs) {
  if (!langs||!langs.length) return '';
  return `<div class="cv-section"><div class="cv-section-title">Languages</div>${langs.map(l=>`<div class="cv-lang"><span class="cv-lang-name">${esc(l.language||'')}</span><span class="cv-lang-badge">${esc(l.level||'')}</span></div>`).join('')}</div>`;
}
function _langsChipsHtml(langs) {
  if (!langs||!langs.length) return '';
  return `<div class="tl-section"><div class="tl-sec-title">Languages</div><div class="tl-lang-chips">${langs.map(l=>`<span class="tl-lang-chip">${esc(l.language||'')}${l.level?`<span class="tl-lang-chip-level">${esc(l.level)}</span>`:''}</span>`).join('')}</div></div>`;
}

// ── Classic ──────────────────────────────
function renderClassic(cv, el) {
  el.className = 'cv-doc';
  const pi=cv.personalInfo||{}, jobs=cv.jobInfos||[], edus=cv.educations||[], skls=cv.skills||[], certs=cv.certifications||[], langs=cv.languages||[], links=pi.socialMediaLinks||[];
  const name=[cv.firstName,cv.lastName].filter(Boolean).join(' ');

  const contactHtml=(pi.address||pi.phone||pi.email)?`<div class="cv-section"><div class="cv-section-title">Contact</div><div class="cv-contact">
    ${pi.address?`<div class="cv-contact-item">${_svgPin()}<span>${esc(pi.address)}</span></div>`:''}
    ${pi.phone?`<div class="cv-contact-item">${_svgPhone()}<span>${esc(pi.phone)}</span></div>`:''}
    ${pi.email?`<div class="cv-contact-item">${_svgMail()}<span>${esc(pi.email)}</span></div>`:''}
  </div></div>`:'';
  const linksHtml=links.length?`<div class="cv-section"><div class="cv-section-title">Links</div><div class="cv-social">${links.map(l=>`<div class="cv-social-entry"><div class="cv-social-name">${esc(l.name||'')}</div><a href="${esc(l.link||'#')}" target="_blank" rel="noopener">${esc(l.link||'')}</a></div>`).join('')}</div></div>`:'';
  const sklsHtml=skls.length?`<div class="cv-section"><div class="cv-section-title">Skills</div>${skls.map(s=>{const lvl={BEGINNER:1,INTERMEDIATE:2,ADVANCED:3,EXPERT:4,MASTER:5}[s.level]||3;return`<div class="cv-skill"><div class="cv-skill-row"><span class="cv-skill-name">${esc(s.skill||'')}</span><span class="cv-skill-level">${lvl}/5</span></div><div class="cv-skill-bar"><div class="cv-skill-fill" style="width:${lvl*20}%"></div></div></div>`;}).join('')}</div>`:'';
  const certsHtml=certs.length?`<div class="cv-section"><div class="cv-section-title">Certifications</div>${certs.map(c=>`<div class="cv-cert"><div class="cv-cert-name">${esc(c.name||'')}</div><div class="cv-cert-meta">${[c.issuer,c.date].filter(Boolean).map(esc).join(' · ')}</div></div>`).join('')}</div>`:'';
  const summaryHtml=cv.summary?`<div class="cv-section"><div class="cv-section-title">Profile</div><div class="cv-summary">${esc(cv.summary)}</div></div>`:'';
  const jobsHtml=jobs.length?`<div class="cv-section"><div class="cv-section-title">Work Experience</div>${jobs.map(j=>{const d=_duties(j);return`<div class="cv-job"><div class="cv-job-top"><div class="cv-job-title">${esc(j.title||j.companyName||'')}</div><div class="cv-job-period">${esc(_period(j))}</div></div><div class="cv-job-company">${esc(j.title?j.companyName:'')}</div>${d.length?`<ul class="cv-job-duties">${d.map(x=>`<li>${esc(x)}</li>`).join('')}</ul>`:''}</div>`;}).join('')}</div>`:'';
  const eduHtml=edus.length?`<div class="cv-section"><div class="cv-section-title">Education</div>${edus.map(e=>`<div class="cv-edu"><span class="cv-edu-school">${esc(e.school||'')}</span><span class="cv-edu-period">${[e.from,e.to].filter(Boolean).join(' – ')}</span></div>`).join('')}</div>`:'';

  el.innerHTML=`<div class="cv-doc-header"><div class="cv-header-inner"><div class="cv-header-text"><div class="cv-doc-name">${esc(name)}</div><div class="cv-doc-position">${esc(cv.position||'')}</div></div>${_photoTag('cv-header-photo')}</div></div><div class="cv-doc-body"><div class="cv-sidebar">${contactHtml}${linksHtml}${sklsHtml}${certsHtml}${_langsSidebarHtml(langs)}</div><div class="cv-main">${summaryHtml}${jobsHtml}${eduHtml}</div></div>${cv.footerConsent?`<div class="cv-footer">${esc(cv.footerConsent)}</div>`:''}`;
}

// ── Executive ────────────────────────────
function renderExecutive(cv, el) {
  el.className = 'cv-doc';
  const pi=cv.personalInfo||{}, jobs=cv.jobInfos||[], edus=cv.educations||[], skls=cv.skills||[], certs=cv.certifications||[], langs=cv.languages||[], links=pi.socialMediaLinks||[];
  const name=[cv.firstName,cv.lastName].filter(Boolean).join(' ');

  const contactHtml=(pi.address||pi.phone||pi.email)?`<div class="cv-section"><div class="cv-section-title">Contact</div><div class="cv-contact">
    ${pi.address?`<div class="cv-contact-item">${_svgPin()}<span>${esc(pi.address)}</span></div>`:''}
    ${pi.phone?`<div class="cv-contact-item">${_svgPhone()}<span>${esc(pi.phone)}</span></div>`:''}
    ${pi.email?`<div class="cv-contact-item">${_svgMail()}<span>${esc(pi.email)}</span></div>`:''}
  </div></div>`:'';
  const linksHtml=links.length?`<div class="cv-section"><div class="cv-section-title">Links</div><div class="cv-social">${links.map(l=>`<div class="cv-social-entry"><div class="cv-social-name">${esc(l.name||'')}</div><a href="${esc(l.link||'#')}" target="_blank" rel="noopener">${esc(l.link||'')}</a></div>`).join('')}</div></div>`:'';
  const sklsHtml=skls.length?`<div class="cv-section"><div class="cv-section-title">Skills</div>${skls.map(s=>{const lvl={BEGINNER:1,INTERMEDIATE:2,ADVANCED:3,EXPERT:4,MASTER:5}[s.level]||3;return`<div class="cv-skill"><div class="cv-skill-row"><span class="cv-skill-name">${esc(s.skill||'')}</span><span class="cv-skill-level">${lvl}/5</span></div><div class="cv-skill-bar"><div class="cv-skill-fill" style="width:${lvl*20}%"></div></div></div>`;}).join('')}</div>`:'';
  const certsHtml=certs.length?`<div class="cv-section"><div class="cv-section-title">Certifications</div>${certs.map(c=>`<div class="cv-cert"><div class="cv-cert-name">${esc(c.name||'')}</div><div class="cv-cert-meta">${[c.issuer,c.date].filter(Boolean).map(esc).join(' · ')}</div></div>`).join('')}</div>`:'';
  const summaryHtml=cv.summary?`<div class="cv-section"><div class="cv-section-title">Profile</div><div class="cv-summary">${esc(cv.summary)}</div></div>`:'';
  const jobsHtml=jobs.length?`<div class="cv-section"><div class="cv-section-title">Work Experience</div>${jobs.map(j=>{const d=_duties(j);return`<div class="cv-job"><div class="cv-job-top"><div class="cv-job-title">${esc(j.title||j.companyName||'')}</div><div class="cv-job-period">${esc(_period(j))}</div></div><div class="cv-job-company">${esc(j.title?j.companyName:'')}</div>${d.length?`<ul class="cv-job-duties">${d.map(x=>`<li>${esc(x)}</li>`).join('')}</ul>`:''}</div>`;}).join('')}</div>`:'';
  const eduHtml=edus.length?`<div class="cv-section"><div class="cv-section-title">Education</div>${edus.map(e=>`<div class="cv-edu"><span class="cv-edu-school">${esc(e.school||'')}</span><span class="cv-edu-period">${[e.from,e.to].filter(Boolean).join(' – ')}</span></div>`).join('')}</div>`:'';

  el.innerHTML=`<div class="cv-doc-header"><div class="cv-header-inner"><div class="cv-header-text"><div class="cv-doc-name">${esc(name)}</div><div class="cv-doc-position">${esc(cv.position||'')}</div></div>${_photoTag('cv-header-photo')}</div></div><div class="cv-doc-body"><div class="cv-sidebar exec-sidebar">${contactHtml}${linksHtml}${sklsHtml}${certsHtml}${_langsSidebarHtml(langs)}</div><div class="cv-main">${summaryHtml}${jobsHtml}${eduHtml}</div></div>${cv.footerConsent?`<div class="cv-footer">${esc(cv.footerConsent)}</div>`:''}`;
}

// ── Timeline ─────────────────────────────
function renderTimeline(cv, el) {
  el.className = 'cv-doc tl-layout';
  const pi=cv.personalInfo||{}, jobs=cv.jobInfos||[], edus=cv.educations||[], skls=cv.skills||[], certs=cv.certifications||[], langs=cv.languages||[], links=pi.socialMediaLinks||[];
  const name=[cv.firstName,cv.lastName].filter(Boolean).join(' ');

  const piItems=[
    pi.address?`<div class="tl-pi-item">${_svgPin()}<div><div class="tl-pi-label">Address</div><div class="tl-pi-value">${esc(pi.address)}</div></div></div>`:'',
    pi.phone?`<div class="tl-pi-item">${_svgPhone()}<div><div class="tl-pi-label">Phone</div><div class="tl-pi-value">${esc(pi.phone)}</div></div></div>`:'',
    pi.email?`<div class="tl-pi-item">${_svgMail()}<div><div class="tl-pi-label">Email</div><div class="tl-pi-value">${esc(pi.email)}</div></div></div>`:'',
    ...links.map(l=>`<div class="tl-pi-item">${_svgLink()}<div><div class="tl-pi-label">${esc(l.name||'Link')}</div><div class="tl-pi-value"><a href="${esc(l.link||'#')}" target="_blank" rel="noopener">${esc(l.link||'')}</a></div></div></div>`)
  ].filter(Boolean);
  const piHtml=piItems.length?`<div class="tl-section"><div class="tl-sec-title">Personal Info</div><div class="tl-pi-grid">${piItems.join('')}</div></div>`:'';

  const summaryHtml=cv.summary?`<div class="tl-section"><div class="tl-sec-title">Profile</div><div class="tl-summary">${esc(cv.summary)}</div></div>`:'';
  const jobsHtml=jobs.length?`<div class="tl-section"><div class="tl-sec-title">Work Experience</div>${jobs.map(j=>{const d=_duties(j);return`<div class="tl-entry"><div class="tl-spine"><div class="tl-dot"></div><div class="tl-line"></div></div><div class="tl-content"><div class="tl-job-top"><div class="tl-job-title">${esc(j.title||j.companyName||'')}</div><div class="tl-period">${esc(_period(j))}</div></div><div class="tl-company">${esc(j.title?j.companyName:'')}</div>${d.length?`<ul class="tl-duties">${d.map(x=>`<li>${esc(x)}</li>`).join('')}</ul>`:''}</div></div>`;}).join('')}</div>`:'';
  const eduHtml=edus.length?`<div class="tl-section"><div class="tl-sec-title">Education</div>${edus.map(e=>`<div class="tl-edu-item"><span class="tl-edu-school">${esc(e.school||'')}</span><span class="tl-edu-period">${[e.from,e.to].filter(Boolean).join(' – ')}</span></div>`).join('')}</div>`:'';
  const sklsHtml=skls.length?`<div class="tl-section"><div class="tl-sec-title">Skills</div><div class="tl-chips">${skls.map(s=>`<span class="tl-chip">${esc(s.skill||'')}</span>`).join('')}</div></div>`:'';
  const certsHtml=certs.length?`<div class="tl-section"><div class="tl-sec-title">Certifications</div>${certs.map(c=>`<div class="tl-cert-item"><span class="tl-cert-name">${esc(c.name||'')}</span><span class="tl-cert-meta">${[c.issuer,c.date].filter(Boolean).map(esc).join(' · ')}</span></div>`).join('')}</div>`:'';

  el.innerHTML=`<div class="cv-doc-header"><div class="cv-header-inner"><div class="cv-header-text"><div class="cv-doc-name">${esc(name)}</div><div class="cv-doc-position">${esc(cv.position||'')}</div></div>${_photoTag('cv-header-photo')}</div></div><div class="cv-doc-body"><div class="tl-main">${piHtml}${summaryHtml}${jobsHtml}${eduHtml}${sklsHtml}${certsHtml}${_langsChipsHtml(langs)}</div></div>${cv.footerConsent?`<div class="cv-footer">${esc(cv.footerConsent)}</div>`:''}`;
}

// ── Lumina ───────────────────────────────
function renderLumina(cv, el) {
  el.className = 'cv-doc lumina-layout';
  const pi=cv.personalInfo||{}, jobs=cv.jobInfos||[], edus=cv.educations||[], skls=cv.skills||[], certs=cv.certifications||[], langs=cv.languages||[], links=pi.socialMediaLinks||[];

  const contactHtml=(pi.address||pi.phone||pi.email)?`<div class="cv-section"><div class="cv-section-title">Contact</div><div class="cv-contact">
    ${pi.address?`<div class="cv-contact-item">${_svgPin()}<span>${esc(pi.address)}</span></div>`:''}
    ${pi.phone?`<div class="cv-contact-item">${_svgPhone()}<span>${esc(pi.phone)}</span></div>`:''}
    ${pi.email?`<div class="cv-contact-item">${_svgMail()}<span>${esc(pi.email)}</span></div>`:''}
  </div></div>`:'';
  const linksHtml=links.length?`<div class="cv-section"><div class="cv-section-title">Links</div><div class="cv-social">${links.map(l=>`<div class="cv-social-entry"><div class="cv-social-name">${esc(l.name||'')}</div><a href="${esc(l.link||'#')}" target="_blank" rel="noopener">${esc(l.link||'')}</a></div>`).join('')}</div></div>`:'';
  const sklsHtml=skls.length?`<div class="cv-section"><div class="cv-section-title">Skills</div>${skls.map(s=>{const lvl={BEGINNER:1,INTERMEDIATE:2,ADVANCED:3,EXPERT:4,MASTER:5}[s.level]||3;return`<div class="cv-skill"><div class="cv-skill-row"><span class="cv-skill-name">${esc(s.skill||'')}</span><span class="cv-skill-level">${lvl}/5</span></div><div class="cv-skill-bar"><div class="cv-skill-fill" style="width:${lvl*20}%"></div></div></div>`;}).join('')}</div>`:'';
  const certsHtml=certs.length?`<div class="cv-section"><div class="cv-section-title">Certifications</div>${certs.map(c=>`<div class="cv-cert"><div class="cv-cert-name">${esc(c.name||'')}</div><div class="cv-cert-meta">${[c.issuer,c.date].filter(Boolean).map(esc).join(' · ')}</div></div>`).join('')}</div>`:'';
  const summaryHtml=cv.summary?`<div class="cv-section"><div class="cv-section-title">Profile</div><div class="cv-summary">${esc(cv.summary)}</div></div>`:'';
  const jobsHtml=jobs.length?`<div class="cv-section"><div class="cv-section-title">Work Experience</div>${jobs.map(j=>{const d=_duties(j);return`<div class="cv-job"><div class="cv-job-top"><div class="cv-job-title">${esc(j.title||j.companyName||'')}</div><div class="cv-job-period">${esc(_period(j))}</div></div><div class="cv-job-company">${esc(j.title?j.companyName:'')}</div>${d.length?`<ul class="cv-job-duties">${d.map(x=>`<li>${esc(x)}</li>`).join('')}</ul>`:''}</div>`;}).join('')}</div>`:'';
  const eduHtml=edus.length?`<div class="cv-section"><div class="cv-section-title">Education</div>${edus.map(e=>`<div class="cv-edu"><span class="cv-edu-school">${esc(e.school||'')}</span><span class="cv-edu-period">${[e.from,e.to].filter(Boolean).join(' – ')}</span></div>`).join('')}</div>`:'';

  el.innerHTML=`<div class="cv-doc-header"><div class="lumina-header-inner"><div class="lumina-header-left"><div class="lumina-firstname">${esc(cv.firstName||'')}</div><div class="lumina-lastname">${esc(cv.lastName||'')}</div><div class="lumina-header-position">${esc(cv.position||'')}</div></div><img class="lumina-photo" id="cvHeaderPhoto" src="${photoDataUrl||''}" alt="" style="display:${photoDataUrl?'block':'none'}"/></div></div><div class="cv-doc-body"><div class="cv-sidebar lumina-sidebar">${contactHtml}${linksHtml}${sklsHtml}${certsHtml}${_langsSidebarHtml(langs)}</div><div class="cv-main">${summaryHtml}${jobsHtml}${eduHtml}</div></div>${cv.footerConsent?`<div class="cv-footer">${esc(cv.footerConsent)}</div>`:''}`;
}

// ══════════════════════════════════════════
//  SAMPLE DATA
// ══════════════════════════════════════════
function loadSampleData() {
  document.getElementById('firstName').value='Łukasz';
  document.getElementById('lastName').value='Pączek';
  document.getElementById('position').value='Test Automation Engineer';
  document.getElementById('address').value='Warsaw, Poland';
  document.getElementById('phone').value='796-457-984';
  document.getElementById('email').value='paczeklukasz90@gmail.com';
  document.getElementById('socialList').innerHTML='';
  addSocialItem({name:'LinkedIn',url:'linkedin.com/in/Lukasz-Paczek'});
  addSocialItem({name:'GitHub',url:'https://github.com/paczek224/spring-rest-assured-playground'});
  document.getElementById('workList').innerHTML='';
  addWorkItem({title:'Senior QA Lead',companyName:'Sii Poland, Rzeszów, Polska',from:'2019-08',currentJob:true,duties:'fintech projects, QA, Test automation, Test management, API testing, UI testing, UAT, Manual testing, CI/CD'});
  addWorkItem({title:'Quality Assurance Specialist',companyName:'Makeitright sp. z o.o',from:'2018-09',to:'2019-08',duties:'logistic projects, Selenium testing, SAFE framework, Jenkins, CI/CD'});
  addWorkItem({title:'Quality Assurance Specialist',companyName:'PGS Software S.A.',from:'2017-03',to:'2018-09',duties:'ecommerce projects, Scrum/agile/Kanban, Manual testing, Accessibility testing'});
  addWorkItem({title:'Software Tester',companyName:'SoftSystem Sp. z o.o',from:'2015-11',to:'2017-03',duties:'medical projects, Manual Testing, Regression Testing, SOAP testing'});
  document.getElementById('eduList').innerHTML='';
  addEduItem({school:'WSiZ – Big Data, Rzeszów',from:'2025-03',to:'2026-02'});
  addEduItem({school:'University of Rzeszów – Political Science',from:'2011-10',to:'2014-07'});
  addEduItem({school:'Tadeusz Rejtan Technical College – Economy',from:'2006-09',to:'2010-06'});
  document.getElementById('skills').value='Java EXPERT\nSpring ADVANCED\nSelenium / Selenium Grid MASTER\nPlaywright EXPERT\nTestNG / JUnit 5 EXPERT\nCucumber ADVANCED\nRest Assured EXPERT\nSQL INTERMEDIATE\nDocker ADVANCED\nOpenShift INTERMEDIATE\nJenkins / GitHub Actions ADVANCED\nTestContainers ADVANCED\nWireMock INTERMEDIATE\nKafka INTERMEDIATE\nElasticsearch / Kibana BEGINNER';
  document.getElementById('certList').innerHTML='';
  addCertItem({name:'Cisco Python Essentials 1',issuer:'NetAcad.com',date:'2025-06'});
  addCertItem({name:'ISTQB Foundation Level',issuer:'SJSI',date:'2017-12'});
  addCertItem({name:'ISTQB Agile Tester',issuer:'SJSI',date:'2017-12'});
  document.getElementById('langList').innerHTML='';
  addLanguageItem({name:'Polish',level:'Native'});
  addLanguageItem({name:'English',level:'B2'});
  addLanguageItem({name:'German',level:'A1'});
  document.getElementById('jobOffer').value='Senior Test Automation Engineer';
  document.getElementById('language').value='eng';
  document.getElementById('enhanceCv').checked=true;
  window.scrollTo({top:0,behavior:'smooth'});
}

// ══════════════════════════════════════════
//  INIT
// ══════════════════════════════════════════
buildThemeButtons();
buildFontButtons();
buildTemplateButtons();
addSocialItem(); addWorkItem(); addEduItem(); addCertItem(); addLanguageItem();
wireFieldValidation();

fetch('/api/env/features')
  .then(r => { if (!r.ok) throw new Error(`HTTP ${r.status}`); return r.json(); })
  .then(f => { if (f.sampleData) document.getElementById('btnLoadSample').style.display = ''; })
  .catch(err => console.warn('[env/features]', err));
